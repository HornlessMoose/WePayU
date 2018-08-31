package WePayU;

import WePayU.employees.Commissioned;
import WePayU.employees.Employee;
import WePayU.employees.Hourly;
import WePayU.employees.Salaried;
import WePayU.saleResult.SaleResult;
import WePayU.syndicate.Syndicate;
import WePayU.syndicate.SyndicateAdditionalFee;
import WePayU.timeCard.TimeCard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

class MainControl {
    private Scanner input = new Scanner(System.in);
    private int option, option2;

    private String name, address, paymentMethod, description;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");


    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Syndicate> syndicates = new ArrayList<>();

    private ArrayList<String> paymentSchedule = new ArrayList<>();


    private Employee employee;


    private int employeeID = 1, ID, syndicateID = 1;
    private float salary, commission, monthlyFee;

    void initialPaymentSchedule(){
        paymentSchedule.add("semanal 1 sexta");
        paymentSchedule.add("mensal $");
        paymentSchedule.add("bi-semanalmente");
    }


    void menu() throws ParseException {
        System.out.println("MENU");
        System.out.println( "1: Adicionar empregado\n" +
                            "2: Remover empregado\n" +
                            "3: Lançar cartão de ponto\n" +
                            "4: Lançar resultado de venda\n" +
                            "5: Lançar taxa de serviço\n" +
                            "6: Alterar detalhes de um empregado\n" +
                            "7: Rodar folha de pagamento para hoje\n" +
                            "8: Undo/Redo\n" +
                            "9: Agenda de pagamento\n" +
                            "10: Criar nova agenda de pagamento\n" +
                            "11: Sair.");


        option = Integer.parseInt(input.nextLine());

        switch (option){
            case 1:
                addEmployee();
                employeeID++;
                menu();
                break;
            case 2:
                removeEmployee();
                menu();
                break;
            case 3:
                submitTimeCard();
                menu();
                break;
            case 4:
                submitSaleResult();
                menu();
                break;
            case 5:
                submitServiceFee();
                menu();
                break;
            case 6:
                updateEmployee();
                menu();
                break;
            case 7:
                executePayroll();
                menu();
                break;
            case 9:
                changePaymentSchedule();
                menu();
                break;
            case 10:
                createNewPaymentSchedule();
                menu();
                break;
            case 11:
                break;

            default:
                showEmployees();
                menu();
        }
    }

    private void executePayroll() throws ParseException {
        Calendar payroll = Calendar.getInstance();

        if(payroll.get(Calendar.DAY_OF_MONTH) == 1){
            payrollToday("mensal 1");
        }

        if(payroll.get(Calendar.DAY_OF_MONTH) == 7){
            payrollToday("mensal 7");
        }

        if(isUsefulDay(payroll)){
             payrollToday("mensal $");
        }

        if (payroll.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            payrollToday("semanal 1 segunda");
        }
        if (payroll.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
            payrollToday("semanal 1 sexta");
        }

    }

    private boolean isUsefulDay(Calendar payroll) {
        int lastDay = payroll.getActualMaximum(Calendar.DAY_OF_MONTH);

        if(payroll.get(Calendar.DAY_OF_MONTH) == lastDay){
            return payroll.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && payroll.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY;

        }


        return false;

    }

    private void payrollToday(String s) throws ParseException {

        for (Employee employee:employees) {
            if(employee.getPaymentSchedule().equals(s)){
                salary = employee.calculateSalary();
                salary -= debitFee(employee);

                System.out.println(employee.toString() + salary);
            }
        }
    }

    private float debitFee(Employee employee) {

        float fees = 0;
        for (Syndicate syndicate:syndicates) {
            if(syndicate.getEmployeeID() == employee.getID()){
                fees = syndicate.getMonthlyFee();

                for (SyndicateAdditionalFee syndicateAdditionalFee: syndicate.getSyndicateAdditionalFees()){
                    fees += syndicateAdditionalFee.getAdditionalFee();
                }
                break;
            }
        }

        return fees;
    }

    private void changePaymentSchedule() {
        System.out.println("informe o ID do empregado:");
        ID = Integer.parseInt(input.nextLine());

        for (Employee employee: employees) {
            if(employee.getID() == ID){
                System.out.println("informe a nova agenda de pagamentos para o empregado:");
                System.out.println("escolha entre: (mensal 1, mensal 7," +
                        " mensal $, semanal 1 segunda, semanal 1 sexta, semanal 2 segunda).");

                employee.setPaymentSchedule(input.nextLine());

                return;

            }
        }

        System.out.println("empregado não encontrado.");
    }

    private void createNewPaymentSchedule() {
        System.out.println("Escolha uma das possiveis nova agenda de pagamento:");
        System.out.println( "1: mensal 1\n" +
                            "2: mensal 7\n" +
                            "3: mensal $\n" +
                            "4: semanal 1 segunda\n" +
                            "5: semanal 1 sexta\n" +
                            "6: semanal 2 segunda\n");

        option = Integer.parseInt(input.nextLine());

        switch (option){
            case 1:
                addMonthly1();
                break;
            case 2:
                addMonthly7();
                break;
            case 3:
                addMonthly$();
                break;
            case 4:
                addWeekly1Monday();
                break;
            case 5:
                addWeekly1Friday();
                break;
            case 6:
                addWeekly2Monday();
                break;
            default:
                break;
        }
    }

    private void addWeekly2Monday() {
        if(!verifyPaymentSchedule("semanal 2 segunda")){
            paymentSchedule.add("semanal 1 segunda");
            System.out.println("Agenda adicionada");
        }
        else{
            System.out.println("Agenda já adicionada, escolha outra.");
        }
    }

    private void addWeekly1Friday() {
        if(!verifyPaymentSchedule("semanal 1 sexta")){
            paymentSchedule.add("semanal 1 sexta");
            System.out.println("Agenda adicionada");
        }
        else{
            System.out.println("Agenda já adicionada, escolha outra.");
        }
    }

    private void addWeekly1Monday() {
        if(!verifyPaymentSchedule("semanal 1 segunda")){
            paymentSchedule.add("semanal 1 segunda");
            System.out.println("Agenda adicionada");
        }
        else{
            System.out.println("Agenda já adicionada, escolha outra.");
        }
    }

    private void addMonthly$() {
        if(!verifyPaymentSchedule("mensal $")){
            paymentSchedule.add("mensal $");
            System.out.println("Agenda adicionada");
        }
        else{
            System.out.println("Agenda já adicionada, escolha outra.");
        }
    }

    private void addMonthly7() {
        if(!verifyPaymentSchedule("mensal 7")){
            paymentSchedule.add("mensal 7");
            System.out.println("Agenda adicionada");
        }
        else{
            System.out.println("Agenda já adicionada, escolha outra.");
        }
    }

    private void addMonthly1() {

        if(!verifyPaymentSchedule("mensal 1")){
            paymentSchedule.add("mensal 1");
            System.out.println("Agenda adicionada");
        }
        else{
            System.out.println("Agenda já adicionada, escolha outra.");
        }
    }

    private boolean verifyPaymentSchedule(String s) {

        for (String paymentSchedule: paymentSchedule) {
            if(paymentSchedule.equals(s)){
                return true;
            }
        }

        return false;

    }

    private void updateEmployee() {
        System.out.println("informe o ID do empregado:");
        ID = Integer.parseInt(input.nextLine());


        for (Employee employee: employees) {

            if(employee.getID() == ID){
                System.out.println("informe qual atributo deseja alterar desse funcionario:");
                System.out.println( "1: Nome\n" +
                                    "2: Endereço\n" +
                                    "3: Tipo(horario, comissionado, assalariado)\n" +
                                    "4: Método de pagamento\n" +
                                    "5: Sindicato\n" +
                                    "6: Identificação no sindicato\n" +
                                    "7: Taxa sindical\n");
                option2 = Integer.parseInt(input.nextLine());

                switch (option2){
                    case 1:
                        changeName(employee);
                        break;
                    case 2:
                        changeAddress(employee);
                        break;
                    case 3:
                        changeType(employee);
                        break;
                    case 4:
                        changePaymentMethod(employee);
                        break;
                    case 5:
                        changeSyndicate(employee);
                        break;
                    case 6:
                        changeSyndicateID(employee);
                        break;
                    case 7:
                        changeSyndicateFee(employee);
                        break;
                }






                return;
            }


        }

    }

    private void changeSyndicateFee(Employee employee) {

        System.out.println("informe o novo valor da taxa sindical:");
        monthlyFee = Float.parseFloat(input.nextLine());

        for (Syndicate syndicate:syndicates) {
            if(employee.getID() == syndicate.getEmployeeID()){
                syndicate.setMonthlyFee(monthlyFee);
                return;
            }
        }
    }

    private void changeSyndicateID(Employee employee) {
        System.out.println("escolha o novo identificador no sindicato.");

        int newSyndicateID = Integer.parseInt(input.nextLine());

        for (Syndicate syndicate: syndicates) {
            if(newSyndicateID == syndicate.getSyndidateID()){
                System.out.println("já existe esse ID no sindicato.\nescolha outro.");
                return;
            }
        }

        for (Syndicate syndicate:syndicates) {
            if(employee.getID() == syndicate.getEmployeeID()){
                syndicate.setSyndidateID(newSyndicateID);
                return;
            }
        }
    }

    private void changeSyndicate(Employee employee) {

        System.out.println("escolha (1: Adicionar ao sindicato, 2: Remover do sindicato.):");

        option2 = Integer.parseInt(input.nextLine());

        switch (option2){
            case 1:
                for (Syndicate syndicate: syndicates) {
                    if(employee.getID() == syndicate.getEmployeeID()){
                        System.out.println("o empregado já participa do sindicato.");
                        return;
                    }

                }
                addEmployeeToSyndicate(employee.getID());
                break;
            case 2:
                for (Syndicate syndicate: syndicates) {
                    if(employee.getID() == syndicate.getEmployeeID()){
                        syndicates.remove(syndicate);
                        return;
                    }
                }
                break;

        }


    }

    private void changePaymentMethod(Employee employee) {
        System.out.println("Informe o novo metodo de pagamento:");

        employee.setPaymentMethod(input.nextLine());
    }

    private void changeType(Employee employee) {

        System.out.println("novo tipo de empregado:\n" +
                "1: Hourly\n" +
                "2: Salaried\n" +
                "3: Commissioned");

        option2 = Integer.parseInt(input.nextLine());

        switch (option2){
            case 1:
                changeToHourly(employee);
                break;
            case 2:
                changeToSalaried(employee);
                break;
            case 3:
                changeToCommissioned(employee);
                break;
            default:
                break;
        }

    }

    private void changeToCommissioned(Employee employee) {
        addCommissioned(employee);
        employees.remove(employee);

    }

    private void changeToSalaried(Employee employee) {
        addSalaried(employee);
        employees.remove(employee);

    }


    private void changeToHourly(Employee employee) {

        addHourly(employee);
        employees.remove(employee);


    }


    private void changeAddress(Employee employee) {
        System.out.println("Novo Endereço:");
        employee.setAddress(input.nextLine());
    }

    private void changeName(Employee employee) {
        System.out.println("Novo Nome:");
        employee.setName(input.nextLine());
    }

    private void submitServiceFee() {
        System.out.println("informe o ID do empregado:");
        ID = Integer.parseInt(input.nextLine());

        System.out.println("informe a descrição do serviço:");
        description = input.nextLine();

        System.out.println("informe a taxa de serviço:");
        monthlyFee = Float.parseFloat(input.nextLine());

        for (Syndicate syndicate: syndicates) {
            if(syndicate.getEmployeeID() == ID){
                syndicate.getSyndicateAdditionalFees().add(new SyndicateAdditionalFee(description, monthlyFee));
            }
        }

    }

    private void submitSaleResult() {
        Calendar date = Calendar.getInstance();

        System.out.println("informe o ID do empregado:");
        ID = Integer.parseInt(input.nextLine());

        System.out.println("informe o valor da venda: ");
        float value = Float.parseFloat(input.nextLine());

        for (Employee employee: employees) {

            if(employee.getID() == ID){
                if (employee instanceof Commissioned) {
                    ((Commissioned) employee).getSaleResults().add(new SaleResult(sdf2.format(date.getTime()), value));

                }
                else{
                    System.out.println("Empregado não é do tipo 'Comissionado' ");
                }
                return;
            }
        }


    }

    private void submitTimeCard() {


        Calendar start = Calendar.getInstance();

        System.out.println("informe o ID do empregado:");
        ID = Integer.parseInt(input.nextLine());


        System.out.println("horário de chegada\nhora:");

        start.set(Calendar.HOUR_OF_DAY, Integer.parseInt(input.nextLine()));

        System.out.println("minutos:");

        start.set(Calendar.MINUTE, Integer.parseInt(input.nextLine()));

        System.out.println("segundos:");

        start.set(Calendar.SECOND, Integer.parseInt(input.nextLine()));

        Calendar end = Calendar.getInstance();


        System.out.println("horário de saida\nhora:");

        end.set(Calendar.HOUR_OF_DAY, Integer.parseInt(input.nextLine()));

        System.out.println("minutos:");

        end.set(Calendar.MINUTE, Integer.parseInt(input.nextLine()));

        System.out.println("segundos:");

        end.set(Calendar.SECOND, Integer.parseInt(input.nextLine()));


        for (Employee employee: employees) {

            if(employee.getID() == ID){
                if (employee instanceof Hourly) {
                    ((Hourly) employee).getTimeCards().add(new TimeCard(sdf.format(start.getTime()), sdf.format(end.getTime())));

                }
                else{
                    System.out.println("Empregado não é do tipo 'Horario' ");
                }
                return;
            }
        }






    }

    private void showEmployees() {
        for (Employee employee:employees) {
            System.out.println(employee);



            if (employee instanceof Commissioned) {
                System.out.println(((Commissioned) employee).getSalary());
                System.out.println(((Commissioned) employee).getCommission());

            }

            else if (employee instanceof Salaried) {
                System.out.println(((Salaried) employee).getSalary());

            }
        }
    }


    private void addEmployee() {
        employee = createEmployee(employeeID);


        System.out.println("Tipo de empregado:\n" +
                            "1: Hourly\n" +
                            "2: Salaried\n" +
                            "3: Commissioned");

        option2 = Integer.parseInt(input.nextLine());

        switch (option2){
            case 1:
                employee.setPaymentSchedule("semanal 1 sexta");
                addHourly();
                break;
            case 2:
                employee.setPaymentSchedule("mensal $");
                addSalaried();
                break;
            case 3:
                employee.setPaymentSchedule("bi-semanalmente");
                addCommissioned();
                break;
            default:
                break;
        }

        System.out.println("Participa do sindicato?(1: Sim, 2: Não):");

        option2 = Integer.parseInt(input.nextLine());

        switch (option2){
            case 1:
                addEmployeeToSyndicate(employeeID);
                syndicateID++;
                break;
            case 2:
                break;

            default:
                break;
        }


    }

    private void addEmployeeToSyndicate(int employeeID) {
        System.out.println("Informe a taxa mensal");
        monthlyFee = Float.parseFloat(input.nextLine());

        syndicates.add(new Syndicate(monthlyFee, employeeID, syndicateID));

    }

    private Employee createEmployee(int ID){
        System.out.println("Nome: ");
        name = input.nextLine();
        System.out.println("Endereço: ");
        address = input.nextLine();
        System.out.println("Metodo de pagamento(1: cheque pelos correios, 2: cheque em mãos, 3: depósito em conta bancária) ");

        option2 = Integer.parseInt(input.nextLine());

        switch (option2){
            case 1:
                paymentMethod = "cheque pelos correios";
                break;
            case 2:
                paymentMethod = "cheque em mãos";
                break;
            case 3:
                paymentMethod = "depósito em conta bancária";
                break;
        }


        employee = new Employee(name, address, ID, paymentMethod);

        return employee;
    }


    private void addHourly() {
        System.out.println("Salario por hora");
        float hourlySalary = Float.parseFloat(input.nextLine());
        employees.add(new Hourly(employee.getName(), employee.getAddress(), employeeID, employee.getPaymentMethod(), hourlySalary));
    }

    private void addHourly(Employee employee) {
        System.out.println("Salario por hora");
        float hourlySalary = Float.parseFloat(input.nextLine());
        employees.add(new Hourly(employee.getName(), employee.getAddress(), employee.getID(), employee.getPaymentMethod(), hourlySalary));
    }

    private void addSalaried() {

        System.out.println("Salario: ");
        salary = Float.parseFloat(input.nextLine());

        employees.add(new Salaried(employee.getName(), employee.getAddress(), employeeID, employee.getPaymentMethod(), salary));
    }

    private void addSalaried(Employee employee) {

        System.out.println("Salario: ");
        salary = Float.parseFloat(input.nextLine());

        employees.add(new Salaried(employee.getName(), employee.getAddress(), employee.getID(), employee.getPaymentMethod(), salary));
    }

    private void addCommissioned() {

        System.out.println("Salario: ");
        salary = Float.parseFloat(input.nextLine());

        System.out.println("Comissão: ");
        commission = Float.parseFloat(input.nextLine());

        employees.add(new Commissioned(employee.getName(), employee.getAddress(), employeeID, employee.getPaymentMethod(), salary,
                commission));



    }

    private void addCommissioned(Employee employee) {

        System.out.println("Salario: ");
        salary = Float.parseFloat(input.nextLine());

        System.out.println("Comissão: ");
        commission = Float.parseFloat(input.nextLine());

        employees.add(new Commissioned(employee.getName(), employee.getAddress(), employee.getID(), employee.getPaymentMethod(), salary,
                commission));



    }

    private void removeEmployee() {

        System.out.println("informe o ID do empregado que deseja remover:");

        ID = Integer.parseInt(input.nextLine());


        for (Employee employee: employees) {

            if(employee.getID() == ID){
                employees.remove(employee);
                System.out.println("empregado removido.");
                return;
            }

        }

        System.out.println("empregado não encontrado.");

    }
}
