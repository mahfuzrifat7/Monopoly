package sample.behindthescene;

import sun.awt.SunHints;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class PlayerWorld implements Runnable {

    private Socket sckt = null;
    private int playerIDwhole;
    private static int numPlayer = 0;
    private static int cp2 =0, cp3= 0, cp4 = 0, qp2 =0, qp3= 0, qp4 = 0;
    private int mwID = 0;
    private boolean breakfromwhile = false;

    private BufferedReader inFromClient = null;
    private PrintWriter outToClient = null;
    private FileWriter writefile = null;
    private BufferedWriter bw = null;


    //this arrays will temporarily hold playerIDwholes of players who are yet
    //to be assigned to any match
    private static int playerIDwholelistc2[] = new int[2];
    private static int playerIDwholelistc3[] = new int[3];
    private static int playerIDwholelistc4[] = new int[4];

    private static int playerIDwholelistq2[] = new int[2];
    private static int playerIDwholelistq3[] = new int[3];
    private static int playerIDwholelistq4[] = new int[4];


    public static int getCp2() {
        return cp2;
    }

    public static int getCp3() {
        return cp3;
    }

    public static int getCp4() {
        return cp4;
    }

    public static int getQp2() {
        return qp2;
    }

    public static int getQp3() {
        return qp3;
    }

    public static int getQp4() {
        return qp4;
    }

    public BufferedReader getInFromClient() {
        return inFromClient;
    }

    public PrintWriter getOutToClient() {
        return outToClient;
    }

    public PlayerWorld(Socket connectionSocket, int playerIDwhole, int ctwoPlayers, int cthreePlayers, int cfourPlayers, int qtwoPlayers, int qthreePlayers, int qfourPlayers) throws IOException {

        this.sckt = connectionSocket;
        this.playerIDwhole = playerIDwhole;

        try
        {
            inFromClient = new BufferedReader(new InputStreamReader(sckt.getInputStream()));
            outToClient = new PrintWriter(sckt.getOutputStream());

        }
        catch (Exception e) {
            System.err.println("Problem in connecting with the server. Exiting main.");
            System.exit(1);
        }

        cp2 = ctwoPlayers;
        cp3 = cthreePlayers;
        cp4 = cfourPlayers;

        qp2 = qtwoPlayers;
        qp3 = qthreePlayers;
        qp4 = qfourPlayers;
        ///constructor ends

        writefile = new FileWriter(GameServer.getFile(), true);
        bw = new BufferedWriter(writefile);
    }

    public void run()
    {
        MonopolyWorld mw;
        String str = new String();
        String mode = new String();


        while (true) ///while loop for taking in players from server and passing them on to monopolyworld
        {

            //System.out.println("pw er run er while loop er shuru");

            try {
                System.out.println(" player "+playerIDwhole+" readline er age str ---"+str);
                str = inFromClient.readLine();
                System.out.println("   player "+playerIDwhole+" username password hoilo "+str);

            } catch (IOException e) {
                e.printStackTrace();
            }

            String newmsg[] = new String[2];
            newmsg =  str.split("###@###");

            String un =newmsg[0];
            String pw =newmsg[1];
            //String fromwhere =newmsg[2];

            //System.out.println("acc r pass hoilo  "+un+"    "+pw);

            //if(newmsg[2].equals("signup"))
            {
                try {
                    // bw.append(un+" "+pw);
                    bw.write(" "+un+" "+pw);
                    bw.newLine();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            /*else if(newmsg[2].equals("login"))
            {
                Scanner sc = null;
                int i=0;
                String temp;
                Vector<String>username = new Vector<>();
                Vector<String>pwVec = new Vector<>();

                try {
                    sc = new Scanner(GameServer.getFile());
                }
                catch (FileNotFoundException fnf)
                {
                    System.out.println(fnf);
                }

                while (sc.hasNext())
                {
                    try {
                        temp = sc.next(); username.add(i, temp);
                        temp = sc.next(); pwVec.add(i, temp);
                        i++;
                    }
                    catch (NullPointerException npe)
                    {
                        npe.printStackTrace();
                    }
                }

                if( !username.contains(un))
                {
                    int idx = username.indexOf(un);
                    if( pwVec.elementAt(idx).equals(pw))
                    {
                        GameServer.pwList.elementAt(playerIDwhole-1).outToClient.println("let him in");
                        GameServer.pwList.elementAt(playerIDwhole-1).outToClient.flush();
                    }


                }
            }*/

            try {
                 mode = inFromClient.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }



            try {
                System.out.println(" player "+playerIDwhole+" readline er age str ---"+str);
                str = inFromClient.readLine();
                System.out.println("   player "+playerIDwhole+" str hoilo "+str);

            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (NumberFormatException npe) {
                System.out.println("nulllptr");
            }


            /*if(str.contains("rolldice"))
            {
                System.out.println("rolldice in playerworld");
                try {
                    outToMW.write(str);
                    outToMW.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("DONE");
                continue;
            }
            else */if(str.equals("2") || str.equals("3") || str.equals("4"))
            {
                numPlayer = Integer.parseInt(str);

                System.out.println(numPlayer+" jon er match khelte chay");


                if(numPlayer == 2 && mode.equals("classic") && cp2<2)
                {
                    cp2 ++;
                    playerIDwholelistc2[cp2-1] = playerIDwhole;/*
                    outToClient.write(String.valueOf(p2));
                    outToClient.flush();*/

                }

                if(numPlayer == 3 && mode.equals("classic") && cp3<3)
                {
                    cp3 ++;
                    playerIDwholelistc3[cp3-1] = playerIDwhole;/*
                    outToClient.write(String.valueOf(p2));
                    outToClient.flush();*/

                }

                if(numPlayer == 4 && mode.equals("classic") && cp4<4)
                {
                    cp4 ++;
                    playerIDwholelistc4[cp4-1] = playerIDwhole;/*
                    outToClient.write(String.valueOf(p2));
                    outToClient.flush();*/

                }

                if(numPlayer == 2 && mode.equals("quick match") && qp2<2)
                {
                    qp2 ++;
                    playerIDwholelistq2[qp2-1] = playerIDwhole;/*
                    outToClient.write(String.valueOf(p2));
                    outToClient.flush();*/

                }

                if(numPlayer == 3 && mode.equals("quick match") && qp3<3)
                {
                    qp3 ++;
                    playerIDwholelistq3[qp3-1] = playerIDwhole;/*
                    outToClient.write(String.valueOf(p2));
                    outToClient.flush();*/

                }

                if(numPlayer == 4 && mode.equals("quick match") && qp4<4)
                {
                    qp4 ++;
                    playerIDwholelistq4[qp4-1] = playerIDwhole;/*
                    outToClient.write(String.valueOf(p2));
                    outToClient.flush();*/

                }

//ফেব্রুয়ারি ৩ ২০১৯ বিকাল ৫ টা ২৮
            /*৩টা নুতুন আরে তে যেইসব প্লেয়ার কানেক্টেড হচ্ছে তাদের গ্লোবাল আইডী টা রেখে দিলাম।
            আরেগুলা এরপর মনোপলি ওয়ার্ল্ডে পাঠাইতে হবে যাতে বোঝা যায় এক ম্যাচে কোন কোণ প্লেয়ার খেলতেসে
            */

               //System.out.println(p2+p3+p4);

                if(numPlayer == 2 && mode.equals("classic") && cp2 == 2)
            {
                mwID++;

                mw = new MonopolyWorld(2, mwID, playerIDwholelistc2, mode);
                GameServer.mwList.add(mw);

                ///loading shesh howar request
                for(int idx=0;idx<playerIDwholelistc2.length;idx++) {
                    GameServer.pwList.elementAt(playerIDwholelistc2[idx] - 1).outToClient.println("loading shesh");
                    GameServer.pwList.elementAt(playerIDwholelistc2[idx] - 1).outToClient.flush();
                }

                ///loading shesh howar confirmation
                for(int idx=0;idx<playerIDwholelistc2.length;idx++) {
                    try {
                        String lmsg = GameServer.pwList.elementAt(playerIDwholelistc2[idx] - 1).inFromClient.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                /*outToClient.print("playerhoyegese");
                outToClient.flush();*/

                Thread t = new Thread(mw);
                t.start();

                cp2 = 0;
                System.out.println("2 joner khela shuru");

                for(int idx=0;idx<playerIDwholelistc2.length;idx++) {
                    GameServer.pwList.elementAt(playerIDwholelistc2[idx] - 1).outToClient.println("enableKoroButtonczKhelaShuru"+String.valueOf(idx));
                    GameServer.pwList.elementAt(playerIDwholelistc2[idx] - 1).outToClient.flush();
                    GameServer.pwList.elementAt(playerIDwholelistc2[idx] - 1).breakfromwhile = true;
                }
            }
                if(numPlayer == 3&& mode.equals("classic")  && cp3 == 3)
                {
                    mwID++;

                    mw = new MonopolyWorld(3, mwID, playerIDwholelistc3, mode);
                    GameServer.mwList.add(mw);


                    ///loading shesh howar request
                    for(int idx=0;idx<playerIDwholelistc3.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistc3[idx] - 1).outToClient.println("loading shesh");
                        GameServer.pwList.elementAt(playerIDwholelistc3[idx] - 1).outToClient.flush();
                    }

                    ///loading shesh howar confirmation
                    for(int idx=0;idx<playerIDwholelistc3.length;idx++) {
                        try {
                            String lmsg = GameServer.pwList.elementAt(playerIDwholelistc3[idx] - 1).inFromClient.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                /*outToClient.print("playerhoyegese");
                outToClient.flush();*/
                    Thread t = new Thread(mw);
                    t.start();

                    cp3 = 0;
                    System.out.println("3 joner khela shuru");

                    for(int idx=0;idx<playerIDwholelistc3.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistc3[idx] - 1).outToClient.println("enableKoroButtonczKhelaShuru"+String.valueOf(idx));
                        GameServer.pwList.elementAt(playerIDwholelistc3[idx] - 1).outToClient.flush();
                        GameServer.pwList.elementAt(playerIDwholelistc3[idx] - 1).breakfromwhile = true;
                    }
                }


                if(numPlayer == 4 && mode.equals("classic")&& cp4 == 4)
                {
                    mwID++;

                    mw = new MonopolyWorld(4, mwID, playerIDwholelistc4, mode);
                    GameServer.mwList.add(mw);


                    ///loading shesh howar request
                    for(int idx=0;idx<playerIDwholelistc4.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistc4[idx] - 1).outToClient.println("loading shesh");
                        GameServer.pwList.elementAt(playerIDwholelistc4[idx] - 1).outToClient.flush();
                    }

                    ///loading shesh howar confirmation
                    for(int idx=0;idx<playerIDwholelistc4.length;idx++) {
                        try {
                            String lmsg = GameServer.pwList.elementAt(playerIDwholelistc4[idx] - 1).inFromClient.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                /*outToClient.print("playerhoyegese");
                outToClient.flush();*/

                    Thread t = new Thread(mw);
                    t.start();

                    cp4 = 0;
                    System.out.println("4 joner khela shuru");

                    for(int idx=0;idx<playerIDwholelistc4.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistc4[idx] - 1).outToClient.println("enableKoroButtonczKhelaShuru"+String.valueOf(idx));
                        GameServer.pwList.elementAt(playerIDwholelistc4[idx] - 1).outToClient.flush();
                        GameServer.pwList.elementAt(playerIDwholelistc4[idx] - 1).breakfromwhile = true;
                    }
                }







                if(numPlayer == 2 && mode.equals("quick match") && qp2 == 2)
                {
                    mwID++;

                    mw = new MonopolyWorld(2, mwID, playerIDwholelistq2, mode);
                    GameServer.mwList.add(mw);

                    ///loading shesh howar request
                    for(int idx=0;idx<playerIDwholelistq2.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistq2[idx] - 1).outToClient.println("loading shesh");
                        GameServer.pwList.elementAt(playerIDwholelistq2[idx] - 1).outToClient.flush();
                    }

                    ///loading shesh howar confirmation
                    for(int idx=0;idx<playerIDwholelistq2.length;idx++) {
                        try {
                            String lmsg = GameServer.pwList.elementAt(playerIDwholelistq2[idx] - 1).inFromClient.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                /*outToClient.print("playerhoyegese");
                outToClient.flush();*/

                    Thread t = new Thread(mw);
                    t.start();

                    qp2 = 0;
                    System.out.println("2 joner khela shuru");

                    for(int idx=0;idx<playerIDwholelistq2.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistq2[idx] - 1).outToClient.println("enableKoroButtonczKhelaShuru"+String.valueOf(idx));
                        GameServer.pwList.elementAt(playerIDwholelistq2[idx] - 1).outToClient.flush();
                        GameServer.pwList.elementAt(playerIDwholelistq2[idx] - 1).breakfromwhile = true;
                    }
                }
                if(numPlayer == 3 && mode.equals("quick match") && qp3 == 3)
                {
                    mwID++;

                    mw = new MonopolyWorld(3, mwID, playerIDwholelistq3, mode);
                    GameServer.mwList.add(mw);


                    ///loading shesh howar request
                    for(int idx=0;idx<playerIDwholelistq3.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistq3[idx] - 1).outToClient.println("loading shesh");
                        GameServer.pwList.elementAt(playerIDwholelistq3[idx] - 1).outToClient.flush();
                    }

                    ///loading shesh howar confirmation
                    for(int idx=0;idx<playerIDwholelistq3.length;idx++) {
                        try {
                            String lmsg = GameServer.pwList.elementAt(playerIDwholelistq3[idx] - 1).inFromClient.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                /*outToClient.print("playerhoyegese");
                outToClient.flush();*/
                    Thread t = new Thread(mw);
                    t.start();

                    qp3 = 0;
                    System.out.println("3 joner khela shuru");

                    for(int idx=0;idx<playerIDwholelistq3.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistq3[idx] - 1).outToClient.println("enableKoroButtonczKhelaShuru"+String.valueOf(idx));
                        GameServer.pwList.elementAt(playerIDwholelistq3[idx] - 1).outToClient.flush();
                        GameServer.pwList.elementAt(playerIDwholelistq3[idx] - 1).breakfromwhile = true;
                    }
                }
                if(numPlayer == 4 &&  mode.equals("quick match") && qp4 == 4)
                {
                    mwID++;

                    mw = new MonopolyWorld(4, mwID, playerIDwholelistq4, mode);
                    GameServer.mwList.add(mw);


                    ///loading shesh howar request
                    for(int idx=0;idx<playerIDwholelistq4.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistq4[idx] - 1).outToClient.println("loading shesh");
                        GameServer.pwList.elementAt(playerIDwholelistq4[idx] - 1).outToClient.flush();
                    }

                    ///loading shesh howar confirmation
                    for(int idx=0;idx<playerIDwholelistq4.length;idx++) {
                        try {
                            String lmsg = GameServer.pwList.elementAt(playerIDwholelistq4[idx] - 1).inFromClient.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                /*outToClient.print("playerhoyegese");
                outToClient.flush();*/

                    Thread t = new Thread(mw);
                    t.start();

                    qp4 = 0;
                    System.out.println("4 joner khela shuru");

                    for(int idx=0;idx<playerIDwholelistq4.length;idx++) {
                        GameServer.pwList.elementAt(playerIDwholelistq4[idx] - 1).outToClient.println("enableKoroButtonczKhelaShuru"+String.valueOf(idx));
                        GameServer.pwList.elementAt(playerIDwholelistq4[idx] - 1).outToClient.flush();
                        GameServer.pwList.elementAt(playerIDwholelistq4[idx] - 1).breakfromwhile = true;
                    }
                }







            }

          /*  else
            {
                outToMW.write("-----");
                outToMW.close();
            }
*/
            /*try {
                numPlayer = Integer.parseInt(inFromClient.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (NullPointerException npe) {
                System.out.println("nulllptr");
            }*/
            //System.out.println("pw er run er while loop er shesh");

            while (!breakfromwhile)
            {

            }
            if(breakfromwhile)
            {
                System.out.println("i am player "+playerIDwhole+ " breaking from while");
                break;
            }
        }

    }

}
