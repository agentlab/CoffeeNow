/**
 *
 */
package ru.telegram.telegrambots;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.LongPollingBot;
import org.telegram.telegrambots.logging.BotLogger;

/**
 * @author amivanoff
 *
 */
@Component()
public class TelegramBotsManager {

    @Reference(policy = ReferencePolicy.DYNAMIC, bind = "bindPollingBot")
    protected List<LongPollingBot> pollingBots = new ArrayList<>();

    @Activate
    public void activate() {
        ApiContextInitializer.init();
    }

    public void bindPollingBot(LongPollingBot bot) {
        System.out.println("Hello");

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try
        {
            telegramBotsApi.registerBot(bot);
        }
        catch (TelegramApiException e)
        {
            BotLogger.error(TelegramBotsManager.class.getName(), e);
        }
    }

}
