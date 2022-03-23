package com.example.changeip;
import java.io.IOException;
import java.util.Scanner;

public class ChangeipApplication {

	public static void main(String[] args)  {

		IpChanger ipChanger= new IpChanger();

		ipChanger.ChangeAllDBServicesIp();
		ipChanger.ChangeServiceWithoutDB();
}}
