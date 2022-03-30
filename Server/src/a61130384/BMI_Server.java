package a61130384;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BMI_Server {

	public static void main(String[] args) {
		try {
			// mở cổng và bắt đầu nghe
			ServerSocket Socketserver = new ServerSocket(9999);
			System.out.println("I'm listening on 9999 port...");
			// luôn luôn lắng nghe
			while(true)
			{
					// khi có 1 yêu cầu tới, ,thì Accept
					// mở 1 Socket để làm viêc với client đó
					Socket socketClient =  Socketserver.accept(); // 
					System.out.print(socketClient.getInetAddress().getHostAddress());
				//  lấy về luồng xuất			
					OutputStream osTcClient = socketClient.getOutputStream();
					OutputStreamWriter Write2Client = new OutputStreamWriter(osTcClient);
					BufferedWriter buffwrite = new BufferedWriter(Write2Client);
					
					InputStream in = socketClient.getInputStream();
					InputStreamReader inReader = new InputStreamReader(in); 
					BufferedReader buffRead = new BufferedReader(inReader);
					// ===== ======Giao tiếp theo giao thức thiết kế==========================
					BufferedReader buffR;
					while(true)
					{
							// ----server gửi qua client mời nhập a---------------------------
							String chuoiNhapa_gui = "Mời nhập a";
							Double a=Double.parseDouble(chuoiNhapa_gui);
							Double chuoiNhapa = a;
							buffwrite.write(chuoiNhapa_gui + "\n");
							buffwrite.flush();
						
							// ----Nhận phản hồi nhập a từ client và hỏi nhập b---------------
							String chuoiNhapa_nhan = buffRead.readLine();
							String chuoiNhapb_gui = "Mời nhập b \n";
							Double b=Double.parseDouble(chuoiNhapb_gui);
							Double chuoiNhapb = b;
							buffwrite.write(chuoiNhapb_gui + "\n");
							buffwrite.flush();
							// ----Nhận về b, gửi nhập phép tính------------------------------
							String chuoiNhapb_nhan = buffRead.readLine();
							float Nhapb_nhanValue = in.read();
							String chuoiNhaptinh_gui = "Mời nhập nhập phép toán (+, -, *, /) \n";
							buffwrite.write(chuoiNhaptinh_gui + "\n");
							buffwrite.flush();
							//-----Nhận phép tính và gửi xử lý--------------------------------
							String chuoiNhaptinh_nhan = buffRead.readLine();
							String chuoiXuly = "Xử lý...";
							buffwrite.write(chuoiXuly + "\n");
							buffwrite.flush();
							
							String pheptoan=buffRead.readLine();
							double ketqua = 0;
							String kqstr,traloi;
							switch (pheptoan) {
							// ====Tính toán BMI ở đây =======================================
							// ====Các lệnh ở đây============================================
							 case "+":
							            System.out.println(a + " + " + b + " = " + (a + b));
							            break;
							 case "-":
							            System.out.println(a + " - " + b + " = " + (a - b));
							            break;
							 case "*":
							            System.out.println(a + " * " + b + " = " + (a * b));
							            break;
							 case "/":
							            if (b == 0) {
							                System.out.println("Số bị chia phải khác 0.");
							            } else {
							                // ép kiểu kết quả về double để có kết quả chính xác
							                System.out.println(a + " / " + b + " = " +  a / b);
							            }
							            break;
							        default:
							            System.out.println("Nhập phép tính không hợp lệ.");
							            

					}
					kqstr = String.valueOf(ketqua);
					buffwrite.write(kqstr+"\n");
					buffwrite.flush();
					
					buffwrite.write("Làm tiếp không?");
					buffwrite.flush();
					String chuoiNhan=buffRead.readLine();
					String chuoiGui=chuoiNhan;
					buffwrite.write(chuoiGui+"\n");
					buffwrite.flush();
					socketClient.close();
					if(chuoiGui.equals("Không")||chuoiGui.equals("không")) break;
				}
								//socketClient.close();
							}
						}
					catch(IOException e)
					{
						e.printStackTrace();
					}
					
				}

			}

			

