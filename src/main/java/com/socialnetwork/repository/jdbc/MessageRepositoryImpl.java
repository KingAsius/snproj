package com.socialnetwork.repository.jdbc;

import com.socialnetwork.entity.Dialogue;
import com.socialnetwork.entity.Message;
import com.socialnetwork.entity.User;
import com.socialnetwork.repository.MessageRepository;
import com.sun.corba.se.spi.orbutil.fsm.Guard;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by Vladislav on 9/17/2016.
 */
@Repository("jdbcMessageRepository")
public class MessageRepositoryImpl implements MessageRepository {
    private static final Logger log = Logger.getLogger(MessageRepositoryImpl.class);
    private static String url;
    private static String userName;
    private static String password;

    private static String DIALOGUES_FOR_USER = "SELECT * FROM public.dialogues WHERE (user1id=? OR user2id=?) AND ismessages=TRUE;";
    private static String GET_DIALOGUE = "SELECT * FROM public.dialogues WHERE users_id=?;";
    private static String INSERT_NEW_DIALOGUE = "INSERT INTO public.dialogues (users_id, user1id, user2id, ismessages) VALUES (?, ?, ?, ?);";
    private static String SET_ISMESSAGES_AS_TRUE = "UPDATE public.dialogues SET ismessages=true WHERE users_id=?;";

    @Override
    public Dialogue getDialogueWithoutMessages(int user1Id, int user2Id) {
        return null;
    }

    @Override
    public void addMessage(Dialogue dialogue, Message message) {
        String dialogueName = dialogue.getUser1Id() + "_" + dialogue.getUser2Id();
        try (Connection connection = getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SET_ISMESSAGES_AS_TRUE))
        {
            preparedStatement.setString(1, dialogueName);
            preparedStatement.execute();

        } catch (SQLException e) { log.info(e.getMessage()); }

        try (Connection connection = getDBConnection();
             PreparedStatement createStatement = connection.prepareStatement("CREATE TABLE messages.m" + dialogueName + "(id serial NOT NULL, message character varying(1000), whoisowner boolean NOT NULL, CONSTRAINT m" + dialogueName + "_pkey PRIMARY KEY (id)) WITH (  OIDS=FALSE); ALTER TABLE messages.m" + dialogueName + " OWNER TO postgres;"))
        {
            createStatement.execute();
        } catch (SQLException e) { log.info(e.getMessage()); }

        try (Connection connection = getDBConnection();
             PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO messages." + "m" + dialogueName + "(message, whoisowner) VALUES (?, ?);"))
        {
            insertStatement.setString(1, message.getMessage());
            insertStatement.setBoolean(2, message.isWhoIsOwner());
            insertStatement.execute();
        } catch (SQLException e) { log.info(e.getMessage()); }
    }

    @Override
    public Dialogue getDialogueWithMessages(int user1Id, int user2Id) {
        String dialogueName = user1Id + "_" + user2Id;
        Dialogue dialogue = new Dialogue(user1Id, user2Id);
        try (Connection connection = getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_DIALOGUE))
        {
            preparedStatement.setString(1, dialogueName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                PreparedStatement messagesStatement = connection.prepareStatement("SELECT * FROM messages." + "m" + dialogueName + ";");
                ResultSet messagesSet = messagesStatement.executeQuery();
                List<Message> messages = new ArrayList<>();
                while (messagesSet.next()) {
                    messages.add(new Message(messagesSet.getString(2), messagesSet.getBoolean(3)));
                }
                dialogue.setMessages(messages);
                messagesSet.close();
                messagesStatement.close();
            }
            else {
                return null;
            }
        }
        catch (SQLException e) {
            log.info(e.getMessage());
        }
        return dialogue;
    }

    @Override
    public void addDialogue(Dialogue dialogue) {
        String dialogueName = dialogue.getUser1Id() + "_" + dialogue.getUser2Id();
        try (Connection connection = getDBConnection();
             PreparedStatement createStatement = connection.prepareStatement(INSERT_NEW_DIALOGUE))
        {
            createStatement.setString(1, dialogueName);
            createStatement.setInt(2, dialogue.getUser1Id());
            createStatement.setInt(3, dialogue.getUser2Id());
            createStatement.setBoolean(4, false);
            createStatement.execute();
            createStatement.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //dialogue returns if ismesssages is true
    @Override
    public List<Dialogue> getDialoguesForUser(int userId) {
        List<Dialogue> dialogues = new ArrayList<>();
        try(Connection connection = getDBConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DIALOGUES_FOR_USER))
        {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                dialogues.add(new Dialogue(resultSet.getInt(2), resultSet.getInt(3)));
            }
        }
        catch (SQLException e) { log.info(e.getMessage()); }
        return dialogues;
    }

    public MessageRepositoryImpl() {
        Properties property = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try(InputStream input = classLoader.getResourceAsStream("db/postgresql.properties")) {
            property.load(input);
            url = property.getProperty("postgre.url");
            userName = property.getProperty("postgre.username");
            password = property.getProperty("postgre.password");
        }
        catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(org.postgresql.Driver.class.getName());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(url, userName, password);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
