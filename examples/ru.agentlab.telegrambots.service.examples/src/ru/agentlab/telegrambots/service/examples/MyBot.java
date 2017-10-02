package ru.agentlab.telegrambots.service.examples;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
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

	@Override
	public String getBotUsername() {
		return BOT_USER_NAME;
	}

	@Override
	public String getBotToken() {
		return BOT_TOKEN;
	}

	@Activate
	public void activate() {
		log.info("Activating component from {} class", MyBot.class.getName()); //$NON-NLS-1$
	}

	@Override
	public void onUpdateReceived(Update update) {
		Message message = update.getMessage();
		if (message != null && message.hasText()) {
			if (message.getText().equals("Расписание"))
				sendMsg(message, "Понедельник:\r\n" + 
						"1)08:30 - 10:05  \r\n" + 
						"2)10:15 - 11:50  \r\n" + 
						"3)12:00 - 13:35 \r\n" + 
						"4)13:50 - 15:25 САМ РАБОТА \r\n" + 
						"5)15:40 - 17:15  \r\n" + 
						"6)17:25 - 19:00  \r\n" + 
						"7)19:10 - 20:45  \r\n" + 
						"\r\n" + 
						"Вторник:    \r\n" + 
						"1)08:30 - 10:05 Методы проектирования инф. систем и технологий 502ю Выхованец В. С.\r\n" + 
						"2)10:15 - 11:50 Экология 515ю(ЧС);Экономика часть 1 515ю(ЗН)\r\n" + 
						"3)12:00 - 13:35 Интеллектуальные системы и технологии 430 Девятков В. В.(ЗН)\r\n" + 
						"4)13:50 - 15:25 \r\n" + 
						"5)15:40 - 17:15  \r\n" + 
						"6)17:25 - 19:00  \r\n" + 
						"7)19:10 - 20:45  \r\n" + 
						" \r\n" + 
						"\r\n" + 
						"Среда:\r\n" + 
						"1)08:30 - 10:05 Экология 413ю(ЧС);БЖД 306ю(ЗН)\r\n" + 
						"2)10:15 - 11:50 БЖД 306ю \r\n" + 
						"3)12:00 - 13:35 Обр.биомет.инф. 533 Петросян О. Г.\r\n" + 
						"4)13:50 - 15:25 \r\n" + 
						"5)15:40 - 17:15  \r\n" + 
						"6)17:25 - 19:00  \r\n" + 
						"7)19:10 - 20:45  \r\n" + 
						"\r\n" + 
						"Четверг:\r\n" + 
						"1)08:30 - 10:05 \r\n" + 
						"2)10:15 - 11:50  \r\n" + 
						"3)12:00 - 13:35 \r\n" + 
						"4)13:50 - 15:25 Экономика часть 1 392 Павлов В. А.\r\n" + 
						"5)15:40 - 17:15 Интеллектуальные системы и технологии 432 Девятков В. В. \r\n" + 
						"6)17:25 - 19:00 Методы проектирования инф. систем и технологий 502ю Выхованец В. С. (ЧС сем) \r\n" + 
						"  Информационные технологии 515ю Асмарян А. Э. (ЗН сем) \r\n" + 
						"7)19:10 - 20:45  \r\n" + 
						"\r\n" + 
						"Пятница:\r\n" + 
						"1)08:30 - 10:05  \r\n" + 
						"2)10:15 - 11:50  \r\n" + 
						"3)12:00 - 13:35 \r\n" + 
						"4)13:50 - 15:25 \r\n" + 
						"5)15:40 - 17:15  \r\n" + 
						"6)17:25 - 19:00  \r\n" + 
						"7)19:10 - 20:45 \r\n" + 
						"\r\n" + 
						"Суббота:\r\n" + 
						"1)08:30 - 10:05 Информационные технологии 432 Асмарян А. Э.(ЗН) \r\n" + 
						"2)10:15 - 11:50 Информационные технологии 432 Асмарян А. Э. \r\n" + 
						"3)12:00 - 13:35 Статистический анализ и прогнозирование 432 Гончар К. В\r\n" + 
						"4)13:50 - 15:25 \r\n" + 
						"5)15:40 - 17:15  \r\n" + 
						"6)17:25 - 19:00  \r\n" + 
						"7)19:10 - 20:45");
			else if (message.getText().equals("Преподаватели"))
				sendMsg(message, "Девятков Владимир Валентинович\r\n" + 
						"заведующий кафедрой, доктор технических наук\r\n" + 
						"Телефон: +7499-263-62-86\r\n" + 
						"\r\n" + 
						"Тихомирова Елизавета Алексеевна\r\n" + 
						"доцент\r\n" + 
						"EMail: elizarti@bmstu.ru\r\n" + 
						"\r\n" + 
						"Павлов Юрий Николаевич\r\n" + 
						"профессор, доктор технических наук\r\n" + 
						"\r\n" + 
						"Телефон: +7 499-263-62-85\r\n" + 
						"EMail: pavlov@bmstu.ru\r\n" + 
						"\r\n" + 
						"Чернега Елена Владимировна\r\n" + 
						"заведующий лабораторией\r\n" + 
						"\r\n" + 
						"Адиенко Наталья Владимировна\r\n" + 
						"ведущий инженер\r\n" + 
						"\r\n" + 
						"Алфимцев Александр Николаевич\r\n" + 
						"доцент, кандидат технических наук\r\n" + 
						"Телефон: +7 499 263-68-14\r\n" + 
						"\r\n" + 
						"Асмарян Альберт Эдуардович\r\n" + 
						"ассистент\r\n" + 
						"\r\n" + 
						"Бабкин Павел Сергеевич\r\n" + 
						"ассистент\r\n" + 
						"\r\n" + 
						"Баскаков Сергей Сергеевич\r\n" + 
						"доцент, кандидат технических наук\r\n" + 
						"\r\n" + 
						"Боровик Ирина Геннадиевна\r\n" + 
						"старший преподаватель, doctor of philosophy\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"Телефон: +7 499 263-62-85\r\n" + 
						"Варфоломеев Александр Алексеевич\r\n" + 
						"доцент, кандидат технических наук\r\n" + 
						"\r\n" + 
						"Видьманов Дмитрий Александрович\r\n" + 
						"ассистент\r\n" + 
						"\r\n" + 
						"Телефон: +79060456626\r\n" + 
						"Выхованец Валерий Святославович\r\n" + 
						"профессор, доктор технических наук\r\n" + 
						"\r\n" + 
						"Телефон: +79168073144\r\n" + 
						"EMail: vykhovanets@bmstu.ru\r\n" + 
						"Web: http://valery.vykhovanets.ru\r\n" + 
						"    \r\n" + 
						"Вялых Константин Михайлович\r\n" + 
						"ассистент\r\n" + 
						"\r\n" + 
						"Гончар Константин Валерьевич\r\n" + 
						"ассистент\r\n" + 
						"\r\n" + 
						"Дядюнов Александр Николаевич\r\n" + 
						"доцент, кандидат технических наук\r\n" + 
						"\r\n" + 
						"Иванов Алексей Михайлович\r\n" + 
						"старший преподаватель\r\n" + 
						"\r\n" + 
						"Коновалов Александр Васильевич\r\n" + 
						"ассистент\r\n" + 
						"\r\n" + 
						"Круппа Захар Петрович\r\n" + 
						"старший преподаватель\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"Леванов Алексей Александрович\r\n" + 
						"ассистент\r\n" + 
						"\r\n" + 
						"Локтев Даниил Алексеевич\r\n" + 
						"ассистент\r\n" + 
						"\r\n" + 
						"Телефон: 8(499)263-62-86\r\n" + 
						"Лычков Игорь Игоревич\r\n" + 
						"старший преподаватель\r\n" + 
						"\r\n" + 
						"Макарчук Владимир Васильевич\r\n" + 
						"доцент, кандидат технических наук\r\n" + 
						"\r\n" + 
						"Мещеринова Ксения Владимировна\r\n" + 
						"старший преподаватель\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"Недашковский Вячеслав Михайлович\r\n" + 
						"доцент, кандидат технических наук\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"Телефон: +7499 263-62-86\r\n" + 
						"Оганов Владимир Игоревич\r\n" + 
						"доцент\r\n" + 
						"\r\n" + 
						"Осипова Нина Витальевна\r\n" + 
						"доцент, кандидат технических наук\r\n" + 
						"\r\n" + 
						"Ошкало Дмитрий Владиславович\r\n" + 
						"ассистент\r\n" + 
						"\r\n" + 
						"Петросян Олег Гарегинович\r\n" + 
						"доцент\r\n" + 
						"\r\n" + 
						"Попов Владислав Сергеевич\r\n" + 
						"ассистент\r\n" + 
						"\r\n" + 
						"Сакулин Сергей Александрович\r\n" + 
						"доцент, кандидат технических наук\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"Самохин Валерий Павлович\r\n" + 
						"доцент, кандидат технических наук\r\n" + 
						"EMail: svp@bmstu.ru\r\n" + 
						"Web: http://inventionstories.blogspot.ru/p/blog-page.html\r\n" + 
						"\r\n" + 
						"Селихов Юрий Родионович\r\n" + 
						"доцент\r\n" + 
						"\r\n" + 
						"Телефон: +7 499 263-68-14\r\n" + 
						"Сидякин Иван Михайлович\r\n" + 
						"доцент, кандидат технических наук\r\n" + 
						"EMail: ivan.sidyakin@bmstu.ru\r\n" + 
						"\r\n" + 
						"Тренин Сергей Алексеевич\r\n" + 
						"старший преподаватель\r\n" + 
						"\r\n" + 
						"Федоров Сергей Владимирович\r\n" + 
						"доцент\r\n" + 
						"\r\n" + 
						"");
			else if (message.getText().equals("О проекте"))
				sendMsg(message, "Привееет!!! \r\n" + 
						"Мы сделали этого бота, чтобы чуть-чуть облегчить нашу суровую студенческую жизнь)\r\n" + 
						"Да и сейчас это классно, модно, молодежно)\r\n" + 
						"\r\n" + 
						"По всем вопросам и предложениям обращаться к \r\n" + 
						"\r\n" + 
						"Кореневой Наташе и Шиловскому Евгению.\r\n"+
						"\r\n" +
						"Руководитель проекта:\r\n"+
						"Иванов Алексей Михайлович");
			else if (message.getText().equals("New"))
				sendMsg(message, "Просиим Вас, Алексей Михайлович, проставить нам положительные оценки "
						+ "Ну очень очень надо, мы понимаем, что на данный момент "
						+ "код мягко говоря не очень, но чтобы мы научились, нам надо окунуться в этот "
						+ "проект с головой, самое главное нам с Натальей это интересно, а когда интересно, то и "
						+ "изучение всего нового происходит быстрее.");
			else
				sendMsg(message, "Хотябы троечку");
		}
	}
	private void sendMsg(Message message, String text) {
		SendMessage sendMessage = new SendMessage();
		//sendMessage.enableMarkdown(true);
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
       // replyKeyboardMarkup.setOneTimeKeyboard(false);
 
	// Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();
 
	// Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
	// Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("Расписание");
        keyboardFirstRow.add("Преподаватели");
 
	// Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
	// Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add("О проекте");
        keyboardSecondRow.add("New");
 
	// Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
	// и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
		sendMessage.setChatId(message.getChatId().toString());
		//sendMessage.setReplyToMessageId(message.getMessageId());//reply message
		sendMessage.setText(text);
		try {
			sendMessage(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	@Deactivate
	public void deactivate() {
		log.info("Deactivating component from {} class", MyBot.class.getName()); //$NON-NLS-1$
	}
}
