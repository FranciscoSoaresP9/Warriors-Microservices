package com.example.changeip;

import java.io.*;
import java.util.Scanner;

public class IpChanger {


    public void ChangeAllDBServicesIp() {
        System.out.println("CHANGE IP OF SERVICE WITH DB");
        Scanner scanner = new Scanner(System.in);
        String newIp = scanner.next();
        try {
            gateWayYmlIp(newIp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ChangeServiceWithoutDB() {
        System.out.println("CHANGE IP OF SERVICES WITHOUT DB");
        Scanner scanner = new Scanner(System.in);
        String newIp = scanner.next();
        try {
            accountIp(newIp);
            resourcesIp(newIp);
            loginIp(newIp);
            pointsIp(newIp);
            statusIp(newIp);
            warriorIp(newIp);
            gateWayIp(newIp);
            loginIp(newIp);
            pageServiceIp(newIp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void resourcesIp(String newIp) throws IOException {
        File file = new File("..\\resourceService\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void accountIp(String newIp) throws IOException {
        File file = new File("..\\account\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void configIp(String newIp) throws IOException {
        File file = new File("..\\config-server\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void gateWayIp(String newIp) throws IOException {
        File file = new File("..\\gateway\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void gateWayYmlIp(String newIp) throws IOException {
        File file = new File("..\\gateway\\src\\main\\resources\\application.yml");
        changeGateWayServer(file, newIp);
    }

    private void loginIp(String newIp) throws IOException {
        File file = new File("..\\login\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void pageServiceIp(String newIp) throws IOException {
        File file = new File("..\\pagesService\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void pointsIp(String newIp) throws IOException {
        File file = new File("..\\points\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void registryIp(String newIp) throws IOException {
        File file = new File("..\\registry\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void statusIp(String newIp) throws IOException {
        File file = new File("..\\status\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void warriorIp(String newIp) throws IOException {
        File file = new File("..\\warrior\\src\\main\\resources\\application.properties");
        change(file, newIp);
    }

    private void change(File file, String newIp) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        int indexOfPort = line.lastIndexOf(":");
        String port = line.substring(indexOfPort, line.length() - 1);

        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("spring.config.import=optional:configserver:http://" + newIp + port + "/" + "\n" +
                "management.endpoints.web.exposure.include=*");
        fileWriter.flush();
        fileWriter.close();
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
                "          uri: http://" + newIp + ":8087/\n" +
                "          predicates:\n" +
                "            - Path=/login/**\n" +
                "        - id: PAGE-SERVICE\n" +
                "          uri: lb://PAGE-SERVICE/\n" +
                "          predicates:\n" +
                "             - Path=/page/**\n" +
                "        - id: RESOURCE-SERVICE\n" +
                "          uri: lb://RESOURCE-SERVICE//\n" +
                "          predicates:\n" +
                "             - Path=/resource/**"+
                "        - id: RESOURCE-SERVICE\n" +
        "                  uri: http://" + newIp + ":8090/\n" +
                "          predicates:\n" +
                "             - Path=/play/**";


    }
}