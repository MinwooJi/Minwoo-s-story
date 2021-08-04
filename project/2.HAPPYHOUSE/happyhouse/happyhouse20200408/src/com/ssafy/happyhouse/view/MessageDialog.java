package com.ssafy.happyhouse.view;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ssafy.happyhouse.util.ViewUtil;

public class MessageDialog extends JFrame implements  ActionListener{
	JFrame  owner;
	JLabel   msgLabel;
	JButton ok;
	int cx, cy;
	public MessageDialog(){
		Panel center = new Panel();
		msgLabel = new JLabel("",JLabel.CENTER);
		center.add(msgLabel);
		JPanel south = new JPanel();
		ok = new JButton("OK");
		ok.addActionListener(this);
		south.add(ok);
		
		add(center,"Center");
		add(south,"South");
		cx = ViewUtil.centerX(this);
		cy = ViewUtil.centerY(this);
	}
	public MessageDialog(JFrame owner){
		this();
		this.owner = owner;
	}
	public void setMain(JFrame owner){
		this.owner = owner;
	}
	public void actionPerformed(ActionEvent  e){
		dispose();
	}
	public void show(String msg){
		msgLabel.setText(msg);
		pack();
		if(owner!=null){
			setLocation(ViewUtil.getX(owner, this), ViewUtil.getY(owner, this));
		}else{
			setLocation(cx - (getWidth()>>1), cy - (getHeight()>>1));
		}
		setVisible(true);
	}
	public static void main(String[] a){
		JFrame t = new JFrame();
		t.setBounds(300, 200, 250, 250);
		t.setVisible(true);
		new MessageDialog(t).show("kkk");
	}
}




