public class StringPractice {
	public static String scroll(String s) {
		return s.substring(1) + s.charAt(0);
	}

	public static String convert(String s) {
		return s.substring(s.indexOf(',') + 2) + " " + s.substring(0, s.indexOf(','));
	}

	public static String switchChars(String s) {
		s = s.replace('1', '2');
		s = s.replace('0', '1');
		return s.replace('2', '0');
	}

	public static void main(String[] args) {
		String cc = "4111 1111 1111 1111";
		String last4 = cc.substring(15);
		String last5 = cc.charAt(13) + last4;

		String random = "";

		for(int i = 0; i < 100; i++) {
			double r = Math.random();
			if(r < 0.45) {
				random += '1';
			}
			else if(r < 0.9) {
				random += '0';
			}
			else {
				random += ' ';
			}
		}

		System.out.println(random);
		System.out.println(switchChars(random));
	}
}