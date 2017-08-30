package bot;

import java.util.List;
import java.util.Random;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TestBot extends TelegramLongPollingBot{

	@Override
    public void onUpdateReceived(Update update) {
		 // We check if the update has a message and the message has text
	    if (update.hasMessage() && update.getMessage().hasText()) {
	        // Set variables
	        String messageText = update.getMessage().getText();
	        String userFirstName = update.getMessage().getChat().getFirstName();
	        String userLastName = update.getMessage().getChat().getLastName();
	        String username = update.getMessage().getChat().getUserName();
	        long userId = update.getMessage().getChat().getId();
	        long chatId = update.getMessage().getChatId();

	        SendMessage message = new SendMessage() // Create a message object object
	                .setChatId(chatId)
	                .setText(messageText);

	        SendPhoto photo = new SendPhoto();
	        photo.setChatId(chatId);
	        photo.setCaption("Photo");
	        List<String> pics = Util.getListOfBoobs(new Random().nextInt(551)+1);
	        String picNum = pics.get(new Random().nextInt(pics.size()));
	        String pictureURL = "http://media.oboobs.ru/boobs_preview/"+picNum+".jpg";
	        photo.setPhoto(pictureURL);

	        Util.log(userFirstName, userLastName, Long.toString(userId), username, messageText, pictureURL);

	        try {
	            //sendMessage(message); // Sending our message object to user
	            sendPhoto(photo);
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }
    }

    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }

}
