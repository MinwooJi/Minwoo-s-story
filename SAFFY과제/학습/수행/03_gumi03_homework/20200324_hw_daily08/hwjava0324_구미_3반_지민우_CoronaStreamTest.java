package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import virus.Corona;

public class hwjava0324_구미_3반_지민우_CoronaStreamTest {
	public static void main(String[] args) {
		List<Corona> list = new ArrayList<Corona>();
		Random r = new Random();
		long count = 0;
		for (int i = 0; i < 10000; i++) {
			String name = "Corona" + i;
			int level = r.nextInt(50);
			String spreadSpeed = "S" + r.nextInt(10);
			list.add(new Corona(name,level,spreadSpeed));
		}
		count = list.stream().map(Corona -> Corona.getLevel()).filter((n)->{return n>30;}).count();
		System.out.println("Corona Count: " + count);
	}
}
