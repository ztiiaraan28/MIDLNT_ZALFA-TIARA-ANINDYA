package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
	Scanner scan = new Scanner(System.in);
	ArrayList<String>kodeList = new ArrayList<>();
	ArrayList<String>namaList = new ArrayList<>();
	ArrayList<String>jenisKelaminList = new ArrayList<>();
	ArrayList<String>jabatanList = new ArrayList<>();
	private Map<String, Double>GajiList = new HashMap<>();
	private Map<String, Integer>karyawanCount = new HashMap<>(); 
	ArrayList<Double>totalGajiList = new ArrayList<>();
	ArrayList<String>kodeBonusList = new ArrayList<>();

	public boolean isAlphabetic (String str) {
		for (int i = 0; i < str.length(); i++) {
				if (!(Character.isAlphabetic(str.charAt(i)))) {
					return false;
				}
			}

		return true;
	}
	void menu() {
		int option;
		do {
			System.out.println("=== PT ChipiChapa ===");
			System.out.println("1. Input data karyawan");
			System.out.println("2. View data karyawan");
			System.out.println("3. Update data karyawan");
			System.out.println("4. Delete data karyawan");
			System.out.print(">> ");
			option = scan.nextInt();scan.nextLine();
			
			if (option == 1) {
				input();
			}else if (option == 2) {
				view();
			}else if (option == 3) {
				update();
			}else if (option == 4) {
				deleteData();
			}
		} while (option !=4);
		deleteData();
	}


	void input (){
		String nama, jk, jabatan;
		do {
			System.out.print("Input nama karyawan: ");
			nama = scan.nextLine();
		} while (isAlphabetic(nama) && nama.length() >= 3);
		namaList.add(nama);

		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan]: ");
			jk = scan.nextLine();
		} while (!(jk.equals("Laki-laki") || jk.equals("Perempuan")));
		jenisKelaminList.add(jk);

		do {
			System.out.print("Input jabatan [Manager| Supervisor | Admin]: ");
			jabatan = scan.nextLine();
		} while (!(jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")));
		jabatanList.add(jabatan);      

		Random rand = new Random();
	    String kode = ""
	            + nama.charAt(rand.nextInt(nama.length())) + nama.charAt(rand.nextInt(nama.length()))
	            + rand.nextInt(10)+ rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);

	    kodeList.add(kode);

		double Gaji = 0;
		if (jabatan.equals("Manager")) {
			Gaji = 8000000;
		}else if (jabatan.equals("Supervisor")) {
			Gaji = 6000000;
		}else if (jabatan.equals("Admin")) {
			Gaji = 4000000;
		}

		int count = karyawanCount.getOrDefault(jabatan, 0);
		karyawanCount.put(jabatan, count + 3);

		double totalGaji = Gaji;

		if (count >= 3) {
			double bonusKaryawan = 0;
		if (jabatan.equals("Manager")) {
			bonusKaryawan = 0.10;
		}else if (jabatan.equals("Supervisor")) {
			bonusKaryawan = 0.075;
		}else if (jabatan.equals("Admin")) {
			bonusKaryawan = 0.05;
		}

			double bonus = Gaji*bonusKaryawan;
			totalGaji += bonus;
			kodeBonusList.add(kode);

			System.out.println("Bonus sebesar " + bonusKaryawan + "%"+ " telah diberikan kepada karyawan dengan id " +kodeBonusList);
		    }
		totalGajiList.add(totalGaji);
		System.out.println("Berhasil menambah karyawan dengan id " +kodeList);
		  }




	void bubbleSort(){
		  int n = namaList.size();
		  for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n-i-1; j++) {
				String current = namaList.get(j);
				String next = namaList.get(j+1);
				if (current.compareToIgnoreCase(next) > 0) {

					String tempNama = namaList.get(j);
					namaList.set(j, namaList.get(j+1));
					namaList.set(j+1, tempNama);

					String tempID = kodeList.get(j);
					kodeList.set(j, kodeList.get(j+1));
					kodeList.set(j+1, tempID);

					String tempJenisKelamin = jenisKelaminList.get(j);
					jenisKelaminList.set(j, jenisKelaminList.get(j+1));
					jenisKelaminList.set(j+1, tempJenisKelamin);

					String tempJabatan = jabatanList.get(j);
					jabatanList.set(j, jabatanList.get(j+1));
					jabatanList.set(j+1, tempJabatan);

					Double tempGaji = totalGajiList.get(j);
					totalGajiList.set(j, totalGajiList.get(j+1));
					totalGajiList.set(j+1, tempGaji);

				}
			}
		}
	}


	void view() {
		bubbleSort();

		if (namaList.isEmpty()) {
			System.out.println("No data!");
	        System.out.println("Press enter to continue...");
		}else {
				System.out.println("==== PT ChipiChapa ==== ");
				for (int i = 0; i < namaList.size(); i++) {
					 System.out.println("No: " + (i + 1));
			         System.out.println("Kode Karyawan: " + kodeList.get(i));
			         System.out.println("Nama Karyawan: " + namaList.get(i));
			         System.out.println("Jenis Kelamin: " + jenisKelaminList.get(i));
			         System.out.println("Jabatan: " + jabatanList.get(i));
			         System.out.println("Gaji Karyawan: " + totalGajiList.get(i));
			}
		}
	}

	void update() {
		view();

		if (namaList.isEmpty()) {
			System.out.println("There are no data to update");
		}else {
			System.out.println("==== PT ChipiChapa ==== ");
			for (int i = 0; i < namaList.size(); i++) {
				 System.out.println("No: " + (i + 1));
		         System.out.println("Kode Karyawan: " + kodeList.get(i));
		         System.out.println("Nama Karyawan: " + namaList.get(i));
		         System.out.println("Jenis Kelamin: " + jenisKelaminList.get(i));
		         System.out.println("Jabatan: " + jabatanList.get(i));
		         System.out.println("Gaji Karyawan: " + totalGajiList.get(i));
			}

			System.out.print("Input nomer urutan karyawan yang ingin di update [1 - " + namaList.size() + "] : ");
			int indexToUpdate = scan.nextInt(); scan.nextLine();

			if (indexToUpdate < 1 || indexToUpdate > namaList.size()) {
				System.out.println("Invalid");
			}else {
				String newNama;
				do {
					System.out.print("Input nama karyawan: ");
					 newNama = scan.nextLine();
				} while (isAlphabetic(newNama) && newNama.length()>= 3);


				System.out.print("Input jenis kelamin [Laki-laki | Perempuan]: ");
				String newjenisKelamin = scan.nextLine();

				if (newjenisKelamin.equals("Laki-laki") || newjenisKelamin.equals("Perempuan")) {
				}


				System.out.print("Input jabatan [Manager| Supervisor | Admin]: ");
				String newjabatan = scan.nextLine();

				if (newjabatan.equals("Manager") || newjabatan.equals("Supervisor") || newjabatan.equals("Admin")) {
				}

				double newGaji = 0;


				if (newjabatan.equals("Manager")) {
					newGaji = 8000000;
				}else if (newjabatan.equals("Supervisor")) {
					newGaji = 6000000;
				}else if (newjabatan.equals("Admin")) {
					newGaji = 4000000;
				}

					if (newNama.equals("0")) {
					}else {
						namaList.set(indexToUpdate-1, newNama);
					}

					if (newjenisKelamin.equals("0")) {
					}else {
						jenisKelaminList.set(indexToUpdate -1, newjenisKelamin);
					}

					if (newjabatan.equals("0")) {
					}else{
						 jabatanList.set(indexToUpdate -1, newjabatan);
						 totalGajiList.set(indexToUpdate -1, newGaji);

					System.out.println("Berhasil mengupdate karyawan dengan id " + kodeList.get(indexToUpdate - 1));
					System.out.println("ENTER to return");

					return;
					}
					}	
					}        
					}

	void deleteData() {
		if (namaList.isEmpty()) {
			System.out.println("There are no data to delete");
		}else {
			int delete = 0;
			 do {
					System.out.print("Input nomer urutan karyawan yang ingin dihapus [1 - " + namaList.size() + "]: ");
				    delete = scan.nextInt(); 
				    scan.nextLine();
				} while (delete <= 0 || delete > namaList.size());

				  int toDelete = delete -1;
				  namaList.remove(toDelete);
				  jenisKelaminList.remove(toDelete);
				  jabatanList.remove(toDelete);
				  totalGajiList.remove(toDelete);


				  System.out.println("Karyawan dengan kode " + kodeList.get(toDelete)+ " berhasil di hapus");
				  System.out.println("ENTER to return");
				  scan.nextLine();	
		}
		   }


	public Main() {	
		menu();
	}

	public static void main(String[] args) {
		new Main();

	}

}