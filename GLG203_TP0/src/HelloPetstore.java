public class HelloPetstore {
	// Point d'entrée de l'application
	public static void main(String args[]) {
		// si vous passez l'argument "controlee"
		if (args[0].equals("controlee")) {
			try {
				controlee();
				System.out.println("Ce texte ne s'affichera pas");
			} catch (Exception e) {
				System.out.println("Hello");
			} finally {
				System.out.println("PetStore!");
			}
		} else {
			noncontrolee();
			System.out.println("Ce texte ne s'affichera pas");
		}
	}

	private static void controlee() throws Exception {
		throw new Exception();
	}

	private static void noncontrolee() {
		throw new RuntimeException();
	}
}