package src.classes;
import java.util.logging.Logger;

public class UserInterface {
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        Logger logger = Logger.getLogger(UserInterface.class.getName());
        int option = 1;

        logger.info("\nSeja bem vindo!\n");
        while (option != 0) {
            logger.info("Menu principal - Selecione uma opcao no menu abaixo:\n");
            logger.info("\n1 - Acessar menu usuario\n2 - Acessar menu de eventos\n3 - Fazer login\n0 - Sair\n");

            option = Integer.parseInt(System.console().readLine());
            if (option != 0 && option != 1 && option != 2 && option != 3) {
                logger.info("\nOpção inválida, tente novamente!");
            }
            else if (option == 1) {
                userMenu();
            }
            else if (option == 0){
                logger.info("\nCerto, programa encerrando...");
            }
        }
    }
    public static void userMenu(){
        int userOption = 1;
        Logger logger = Logger.getLogger(UserInterface.class.getName());
        logger.info("\nVoce esta no menu usuario, selecione uma opção abaixo:\n");

        while (userOption != 0){
            logger.info("\n1 - Cadastrar novo usuario\n2 - Listar usuários\n3 - Buscar usuario\n4 - Atualizar usuario\n5 - Deletar usuario\n0 - Sair\n");
            userOption = Integer.parseInt(System.console().readLine());

            if (userOption != 0 && userOption != 1 && userOption != 2 && userOption != 3 && userOption != 4 && userOption != 5) {
                logger.info("\nOpção inválida, tente novamente!");
            }
            else if (userOption == 1){
                User newUser = User.createUser();
                User.insertUser(newUser);                
            }
            else if (userOption == 2){
                User.listUsers("");
            }
            else if (userOption == 3){
                logger.info("\nDigite o documento do usuário que deseja buscar: ");
                String userDocument = System.console().readLine();
                User.listUsers(userDocument);
            }
            else if (userOption == 4){
                logger.info("\nDigite o documento do usuário que deseja atualizar: ");
                String userDocument = System.console().readLine();
                User.listUsers(userDocument);
                User updateUserData = User.createUser();
                updateUserData.updateUser(updateUserData, userDocument);
            }
            else if (userOption == 5){
                User.listUsers("");
                logger.info("\nDigite o documento do usuário que deseja deletar: ");
                String userDocument = System.console().readLine();
                User.listUsers(userDocument);
                logger.info("\nDigite 1 para confirmar a exclusão, ou qualquer outra tecla para retornar ao menu anterior: ");
                int confirm = Integer.parseInt(System.console().readLine());
                if (confirm == 1){
                    User.deleteUser(userDocument);
                }
                else{
                    logger.info("\nRetornando ao menu anterior.");
                }
            }
            else if (userOption == 0) {
                logger.info("\nRetornando ao menu principal.");
                mainMenu();
            }
        }
    }

    public static void eventMenu(){
        //TODO: Criar menu de eventos
    }
}
