package com.example.changeip;

import java.io.*;
import java.util.Scanner;

public class IpChanger {



    public void ChangeServiceIp() {
        System.out.println("CHANGE IP OF SERVICES ");
        Scanner scanner = new Scanner(System.in);
        String newIp = scanner.next();
        try {
            gateWayYmlIp(newIp);
            accountIp(newIp);
            resourcesIp(newIp);
            loginIp(newIp);
            pointsIp(newIp);
            statusIp(newIp);
            warriorIp(newIp);
            gateWayIp(newIp);
            loginIp(newIp);
            pageServiceIp(newIp);
            playIp(newIp);
            registryAccountIp(newIp);
         //   configIp(newIp);
            changeRegistryYml(newIp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void resourcesIp(String newIp) throws IOException {
        File file = new File("..\\resourceService\\src\\main\\resources\\application.properties");
        changeWithOutDataBase(file, newIp, "Resource Micro Service");
    }

    private void accountIp(String newIp) throws IOException {
        File file = new File("..\\account\\src\\main\\resources\\application.properties");
        changeWithDataBase(file, newIp, "Account Micro Service");
    }

    private void configIp(String newIp) throws IOException {
        File file = new File("..\\config-server\\src\\main\\resources\\application.properties");
        changeWithOutDataBase(file, newIp, "Config Micro Service");
    }

    private void gateWayIp(String newIp) throws IOException {
        File file = new File("..\\gateway\\src\\main\\resources\\application.properties");
        changeWithOutDataBase(file, newIp, "Gate Way Micro Service");
    }

    private void gateWayYmlIp(String newIp) throws IOException {
        File file = new File("..\\gateway\\src\\main\\resources\\application.yml");
        changeGateWayServer(file, newIp);
    }

    private void loginIp(String newIp) throws IOException {
        File file = new File("..\\login\\src\\main\\resources\\application.properties");
        changeWithOutDataBase(file, newIp, "Login Micro Service");
    }

    private void pageServiceIp(String newIp) throws IOException {
        File file = new File("..\\pagesService\\src\\main\\resources\\application.properties");
        changeWithOutDataBase(file, newIp, "Page Micro Service");
    }

    private void pointsIp(String newIp) throws IOException {
        File file = new File("..\\points\\src\\main\\resources\\application.properties");
        changeWithDataBase(file, newIp, "Points Service");
    }

    private void registryIp(String newIp) throws IOException {
        File file = new File("..\\registry\\src\\main\\resources\\application.properties");
        changeWithOutDataBase(file, newIp, "Registry Micro Service");
    }

    private void statusIp(String newIp) throws IOException {
        File file = new File("..\\status\\src\\main\\resources\\application.properties");
        changeWithDataBase(file, newIp, "Status Micro Service");
    }

    private void warriorIp(String newIp) throws IOException {
        File file = new File("..\\warrior\\src\\main\\resources\\application.properties");
        changeWithDataBase(file, newIp, "Warrior Micro Service");
    }

    private void playIp(String newIp) throws IOException {
        File file = new File("..\\play\\src\\main\\resources\\application.properties");
        changeWithOutDataBase(file, newIp, "Play Micro Service");
    }

    private void registryAccountIp(String newIp) throws IOException {
        File file = new File("..\\accountRegistry-service\\src\\main\\resources\\application.properties");
        changeWithOutDataBase(file, newIp, "Registry Account Micro Service");
    }

    private String choseDataBase(String serviceName) {
        System.out.println("Insert the name of Data Base of" + serviceName);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    private void changeWithDataBase(File file, String newIp, String serviceName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        int indexOfPort = line.lastIndexOf(":");
        String port = line.substring(indexOfPort, line.length() - 1);

        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("spring.config.import=optional:configserver:http://" + newIp + port + "/" + "\n" +
                "management.endpoints.web.exposure.include=*\n");
        fileWriter.write("spring.datasource.driver-class-name=com.mysql.jdbc.Driver\n" +
                "spring.datasource.url=jdbc:mysql://localhost:3306/" + choseDataBase(serviceName) + "?createDatabaseIfNotExist=true\n" +
                "spring.datasource.username=root\n" +
                "spring.datasource.password=SAniCA2803\n" +
                "\n" +
                "database.url=http://bacd-87-196-24-58.ngrok.io\n" +
                "\n" +
                "## Hibernate Properties\n" +
                "# The SQL dialect makes Hibernate generate better SQL for the chosen database\n" +
                "spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect\n" +
                "\n" +
                "# Hibernate ddl auto (create, create-drop, validate, update)\n" +
                "spring.jpa.hibernate.ddl-auto = update\n" +
                "\n" +
                "\n" +
                "spring.jpa.properties.hibernate.show_sql = true\n" +
                "spring.jpa.properties.hibernate.format_sql = true");
        fileWriter.flush();
        fileWriter.close();
        System.out.println(serviceName + " Configured");
    }

    private void changeWithOutDataBase(File file, String newIp, String serviceName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        int indexOfPort = line.lastIndexOf(":");
        String port = line.substring(indexOfPort, line.length() - 1);

        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("spring.config.import=optional:configserver:http://" + newIp + port + "/" + "\n" +
                "management.endpoints.web.exposure.include=*\n");
        fileWriter.flush();
        fileWriter.close();
        System.out.println(serviceName + " Configured");
    }

    private void changeGateWayServer(File file, String newIp) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        int indexOfPort = line.lastIndexOf(":");
        String port = line.substring(indexOfPort, line.length() - 1);

        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write(buildProperties(newIp));
        fileWriter.flush();
        fileWriter.close();
    }

    private String buildProperties(String newIp) {
        return "server:\n" +
                "  port: 8088 \n\n" +

                "spring:\n" +
                "  application:\n" +
                "    name: API-GATEWAY\n" +
                "  cloud:\n" +
                "   gateway:\n" +
                "     routes:\n" +
                "        - id: ACCOUNT-SERVICE\n" +
                "          uri: http://" + newIp + ":8081/\n" +
                "          predicates:\n" +
                "            - Path=/account/**\n" +
                "        - id: WARRIOR-SERVICE\n" +
                "          uri: http://" + newIp + ":8083/\n" +
                "          predicates:\n" +
                "            - Path=/warrior/**\n" +
                "        - id: POINTS-SERVICE\n" +
                "          uri: http://" + newIp + ":8084/\n" +
                "          predicates:\n" +
                "            - Path=/points/**\n" +
                "        - id: STATUS-SERVICE\n" +
                "          uri: http://" + newIp + ":8085/\n" +
                "          predicates:\n" +
                "            - Path=/status/**\n" +
                "        - id: LOGIN-SERVICE\n" +
                "          uri: lb://LOGIN-SERVICE/\n" +
                "          predicates:\n" +
                "            - Path=/login/**\n" +
                "        - id: PAGE-SERVICE\n" +
                "          uri: lb://PAGE-SERVICE/\n" +
                "          predicates:\n" +
                "             - Path=/page/**\n" +
                "        - id: RESOURCE-SERVICE\n" +
                "          uri: lb://RESOURCE-SERVICE//\n" +
                "          predicates:\n" +
                "             - Path=/resource/**\n" +
                "        - id: PLAY-SERVICE\n" +
                "          uri: http://" + newIp + ":8090/\n" +
                "          predicates:\n" +
                "             - Path=/play/**\n" +
                "        - id: REGISTRYACCOUNT-SERVICE\n" +
                "          uri: http://" + newIp + ":8091/\n" +
                "          predicates:\n" +
                "             - Path=/registryaccount/**\n" +
                "        - id: REGISTRYWARRIOR-SERVICE\n" +
                "          uri: http://" + newIp + ":8092/\n" +
                "          predicates:\n" +
                "             - Path=/registrywarrior/**";


    }

    private void changeRegistryYml(String newIp) throws IOException {
        File file = new File("..\\registry\\src\\main\\resources\\application.yml");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        int indexOfPort = line.lastIndexOf(":");
        String port = line.substring(indexOfPort, line.length() - 1);

        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("server:\n" +
                "  port: 8761\n" +
                "\n" +
                "\n" +
                "eureka:\n" +
                "  cliente:\n" +
                "    register-with-eureka: false\n" +
                "    fetch-registry: false\n" +
                "    serviceUrl:\n" +
                "      defaultZone: " + newIp + ":8761/eureka/");
        fileWriter.flush();
        fileWriter.close();

    }
}

