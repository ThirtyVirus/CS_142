/**
 * Created by danielshen on 4/25/17.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WoolieBattleRoyale {

    public WoolieBattleRoyale(){
        /**
         *
         */
    }

    public static void main(String[] args) throws IOException {
        /**
         * Main function to simulate a Battle Royale.
         Reads the file
         Makes a list of Woolies to battle
         Makes a sports complex
         Creates a troll and tells the troll to start the battle royale.

         Parameters:
         args - input for file to read from
         */

        ArrayList<Woolie> woolies = new ArrayList<>();
        int battles = 0;

        try
                (BufferedReader read = new BufferedReader(new FileReader(args[0]))){
            battles = Integer.parseInt(read.readLine()); String line = read.readLine();
            while (line != null){

                String[] newWoolie = line.split(",");
                Woolie wol = new Woolie(newWoolie);
                woolies.add(wol);
                line = read.readLine();
            }
        }
        catch(FileNotFoundException nfe){
            throw new IOException(nfe);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Troll troll = new Troll(woolies, new SportsComplex(battles));
        troll.beginBattleRoyale();
    }
}
