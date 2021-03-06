package sample;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import network.*;

public class MsgService {
	private TextFlow textflow;//tf1 pointer to the first window's TextFlow
	//user id is different for each window.
	Client client;
	//////////////////////////////////////////////////
	final static int serverPort=5000;
	final static String serverIp="127.0.0.1";
	final private String userName="Rose";
	//////////////////////////////////////////////////
	MsgService(TextFlow tf)
	{
		this.textflow=tf;
		client=new Client(serverIp,serverPort,this);
	}
	public void send(Text message) throws SQLException//store a new message into the history
	{
		Msg m=new Msg(userName,message.getText(),message.getStyle());
		client.sendMessage(m);
		updateTextFlow(m);
	}
	public synchronized void updateTextFlow(Msg m)//update each TextFlow in both windows.
	{
		textflow.getChildren().addAll(m.getMsgInfo(),m.getMsgContent());	
	}
}
