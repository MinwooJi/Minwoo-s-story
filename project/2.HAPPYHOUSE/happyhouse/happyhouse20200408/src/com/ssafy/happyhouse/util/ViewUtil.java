package com.ssafy.happyhouse.util;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * Java 관통 프로제트에서    화면을 표시할 위치를 구하는 클래스  
 */
public class ViewUtil {
	public static int centerX(Frame self){
		Toolkit  tool = self.getToolkit();
		Dimension  screen =   tool.getScreenSize();
		return screen.width>>1 ;
	}
	public static int centerY(Frame self){
		Toolkit  tool = self.getToolkit();
		Dimension  screen =   tool.getScreenSize();
		return screen.height>>1;
	}
	public static int getX(Frame owner, Frame self){
		if(owner!=null && self != null){
			 return owner.getX() + (owner.getWidth()>>1) - ( self.getWidth()>>1);
		}
		return  0;
	}
	public static int getY(Frame owner, Frame self){
		if(owner!=null && self != null){
			 return owner.getY() + (owner.getHeight()>>1) - ( self.getHeight()>>1);
		}
		return  0;
	}
	public static int nextX(Frame owner, Frame self){
		if(owner!=null && self != null){
			Toolkit  tool = self.getToolkit();
			Dimension  screen =   tool.getScreenSize();
			int x = owner.getX();
			int width=owner.getWidth();
			int selfWidth = self.getWidth();
			if(x+width+selfWidth <screen.getWidth()) {
				return x+width;
			}else {
				return x-selfWidth;
			}
		}
		return  0;
	}
	public static int nextY(Frame owner, Frame self){
		if(owner!=null && self != null){
			 return owner.getY();
		}
		return  0;
	}
	
}
