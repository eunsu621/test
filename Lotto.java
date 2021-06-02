public class Lotto {

	public static void main(String[] args) {
		
		// 로또프로그램
		int[] lotto = new int[45];
		
		for (int i = 0; i < lotto.length; i++ ) {
			lotto[i] = i + 1; // 45개의 숫자를 차례대로 넣는다
		}
		
		int r1 = 0;
		int r2 = 0;
		
		for (int i = 0; i < lotto.length * 2; i++) {
			int temp;
			r1 = (int)(Math.random() * 45);	// 0~44까지의 난수가 담김(인덱스라 그럼)
			r2 = (int)(Math.random() * 45);
			
			// 숫자 섞기(swap)
			temp = lotto[r1];
			lotto[r1] = lotto[r2];
			lotto[r2] = temp;
		}
		System.out.print("오늘의 번호는 : ");
		
		for (int i = 0; i < 6; i++ ) {
			System.out.print(lotto[i] + " ");
		}
	}
}
