package ru.agentlab.telegrambots.service.examples;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.LongPollingBot;

import ru.telegram.telegrambots.bots.TelegramLongPollingBot;

/**
 * @author amivanoff
 *
 */
@Component(service = LongPollingBot.class)
public class MyBot extends TelegramLongPollingBot {

    static Logger log = LoggerFactory.getLogger(MyBot.class);

    public static final String BOT_TOKEN = System.getProperty("MyBotToken", ""); //$NON-NLS-1$ //$NON-NLS-2$
    public static final String BOT_USER_NAME = System.getProperty("MyBotUserName", ""); //$NON-NLS-1$ //$NON-NLS-2$

    @Activate
    public void activate() {
        log.info("Activating component from {} class", MyBot.class.getName()); //$NON-NLS-1$
    }

    @Override
    public String getBotUsername() {
        return BOT_USER_NAME;
    }

    @Override
    public void onUpdateReceived(Update update) {
        //check if the update has a message
        if (update.hasMessage())
        {
            Message message = update.getMessage();

            //check if the message has text. it could also  contain for example a location ( message.hasLocation() )
            if (message.hasText())
            {

                //create a object that contains the information to send back the message
                SendMessage sendMessageRequest = new SendMessage();
                //who should get the message? the sender from which we got the message...
                sendMessageRequest.setChatId(message.getChatId().toString());
                sendMessageRequest.setText("you said: " + message.getText()); //$NON-NLS-1$
                try
                {
                    sendMessage(sendMessageRequest); //at the end, so some magic and send the message ;)
                }
                catch (TelegramApiException e)
                {
                    log.error("Unable to send message", e);
                }
            }
        }
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Deactivate
    public void deactivate() {
        log.info("Deactivating component from {} class", MyBot.class.getName()); //$NON-NLS-1$
    }
}
