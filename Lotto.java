package github;

public class Lotto {

	public static void main(String[] args) {
		
		// �ζ����α׷�
		int[] lotto = new int[45];
		
		for (int i = 0; i < lotto.length; i++ ) {
			lotto[i] = i + 1;
			// System.out.print(lotto[i] + " ");
		}
		
		int r1 = 0;
		int r2 = 0;
		
		for (int i = 0; i < lotto.length * 2; i++) {
			int temp;
			r1 = (int)(Math.random() * 45);	// 0~44������ ����(�ε����� �׷�)
			r2 = (int)(Math.random() * 45);
			
			temp = lotto[r1];
			lotto[r1] = lotto[r2];
			lotto[r2] = temp;
		}
		
		System.out.print("������ ��ȣ�� : ");
		for (int i = 0; i < 6; i++ ) {
			System.out.print(lotto[i] + " ");
		}

	}

}
