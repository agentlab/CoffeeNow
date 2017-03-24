package ru.telegram.telegrambots;

import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.generics.BotSession;

import ru.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief TODO
 * @date 29 of October of 2016
 */
public final class ApiContextInitializer {
    private ApiContextInitializer() {
    }

    public static void init() {
        ApiContext.register(BotSession.class, DefaultBotSession.class);
    }
}
