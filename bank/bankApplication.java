import java.util.Scanner;

public class BankApplication {
	
	private static Account[] accountArray = new Account[100];
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		
		boolean run = true;
		while(run) {
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.입금 | 4.출금 | 5.이체 | 6.종료");
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println("선택> ");
			
			int selectNo = scanner.nextInt();
			
			if (selectNo == 1) {
				createAccount();
			} else if (selectNo == 2) {
				accountList();
			} else if (selectNo == 3) {
				deposit();
			} else if (selectNo == 4) {
				withdraw();
			} else if (selectNo == 5)  {
				transfer();
			} else {
				run = false;
			}
		}
		System.out.println("프로그램 종료");
	}

	// 계좌 생성 메소드
	private static void createAccount() {
		System.out.println("ㅡㅡㅡㅡ");
		System.out.println("계좌생성");
		System.out.println("ㅡㅡㅡㅡ");
		
		System.out.print("계좌번호: ");
		String ano = scanner.next();
		
		System.out.print("계좌주 : ");
		String owner = scanner.next();
		
		System.out.print("초기입금액 : ");
		int balance = scanner.nextInt();
		
		Account newAccount = new Account(ano, owner, balance);	// 내가 입력한 정보로 객체 생성
		
		// 비어있는 배열의 자리에 account를 차례로 넣기 위해 반복문을 돌림
		for (int i = 0; i < accountArray.length; i++) {
			
			if (accountArray[i] == null) {	
				
				// 비어있는 자리에 account객체 넣기(0번이 이미 채워져있으면 1번이 채워짐)
				accountArray[i] = newAccount;	
				break;
			}
		}
		System.out.println("결과 : 계좌가 생성되었습니다.");
	}
	
	
	// 계좌 조회 메서드
	private static void accountList() {
		System.out.println("ㅡㅡㅡㅡ");
		System.out.println("계좌목록");
		System.out.println("ㅡㅡㅡㅡ");
		
		// accountArray에 들어있는 account에 하나씩 접근해서 해당 필드의 값을 불러오기 위한 반복문
		for (int i = 0; i < accountArray.length; i++) {
			
			if (accountArray[i] != null) {
				System.out.print(accountArray[i].getAno() + " ");
				System.out.print(accountArray[i].getOwner() + " ");
				System.out.print(accountArray[i].getBalance() + " ");
				System.out.println();
			}
		}
	}
	
	// 예금 메서드
	private static void deposit() {
		System.out.println("ㅡㅡㅡㅡ");
		System.out.println("입금");
		System.out.println("ㅡㅡㅡㅡ");
		
		System.out.print("입금할 계좌번호: ");
		String ano = scanner.next();
		
		System.out.print("입금액 : ");
		int money = scanner.nextInt();
		
		Account srchAcc = findAccount(ano);
		
		if (srchAcc != null) {
			srchAcc.setBalance(srchAcc.getBalance() + money);
			System.out.println(srchAcc.getAno() + "계좌에 " + money + "원 입금 완료");
		} else {
			System.out.println(" 계좌번호를 다시 확인해주세요");
		}
	}
	
	// 출금 메서드
	private static void withdraw() {
		System.out.println("ㅡㅡㅡㅡ");
		System.out.println("출금");
		System.out.println("ㅡㅡㅡㅡ");
		
		System.out.print("출금할 계좌번호: ");
		String ano = scanner.next();
		
		System.out.print("출금액 : ");
		int money = scanner.nextInt();
		
		Account srchAcc = findAccount(ano);
		
		if (srchAcc != null) {
			
			if (srchAcc.getBalance() < money) {
				System.out.println("출금액이 부족합니다");
			} else {
				srchAcc.setBalance(srchAcc.getBalance() - money);
				System.out.println(srchAcc.getAno() + "계좌에서 " + 
				money + "원 출금 완료");
			}
			return;	// return 타입이 없을 때 return 쓰면 해당 함수 즉시 멈춰주세요 의미
		}
			System.out.println("계좌번호를 다시 확인해주세요");
	}
	
	// 계좌 이체 메소드
	private static void transfer() {
		System.out.println("ㅡㅡㅡㅡ");
		System.out.println("이체");
		System.out.println("ㅡㅡㅡㅡ");
		
		System.out.print("내 계좌번호: ");
		String send = scanner.next();
		
		System.out.print("상대방 계좌번호: ");
		String recieve = scanner.next();
		
		System.out.print("이체 금액 : ");
		int money = scanner.nextInt();
		
		Account sendAcc = findAccount(send);
		Account recieveAcc = findAccount(recieve);
		
		if (sendAcc != null && recieveAcc != null) {
			
			if (sendAcc.getBalance() < money) {
				System.out.println("이체할 금액이 부족합니다.");
			} else {
				sendAcc.setBalance(sendAcc.getBalance() - money);
				recieveAcc.setBalance(recieveAcc.getBalance() + money);
				System.out.println(sendAcc.getAno() + "계좌에서 " + recieveAcc.getAno() + 
						"계좌로 " + money + "원 이체 완료");
			}
			
		} else {
			System.out.println("계좌번호를 확인해주시기 바랍니다.");
		}
	}
	
	
	// 계좌 찾는 메소드
	private static Account findAccount(String ano) {
		
		Account account = null;
		
		for (int i = 0; i < accountArray.length; i++) {
			
			if (accountArray[i] != null) {

				if (accountArray[i].getAno().equals(ano)) {
					account = accountArray[i];
					break;
				}
			}
		}
		return account;
	}
}
