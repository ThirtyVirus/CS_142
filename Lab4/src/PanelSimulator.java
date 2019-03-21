import java.util.Scanner;

/**
 * Created by Brandon Calabrese on 2/20/2017.
 *
 * Runs the electrical system simulation
 */
public class PanelSimulator extends Object {

    //Main method.
    public static void	main(String[] args){
        runSimulation();
    }

    //Run simulation
    static void runSimulation(){
        Scanner sc = new Scanner(System.in);

        Component root = null;

        while(true){
            String command = sc.nextLine();
            String[] word = command.split(" ");
            //System.out.println("PanelSimulator input: " + command);

            switch(word[0]){
                case "A":

                    Component source = null;
                    if (root != null){
                        source = findComponent(root, word[3]);

                        if (findComponent(root, word[2]) != null){
                            System.out.println("Component with name '" + word[2] + "' already exists.");
                            break;
                        }

                        if (source == null){
                            System.out.println("Parent '" + word[3] + "' does not exist");
                            break;
                        }
                    }

                    switch (word[1]){
                        case "C":
                            //ADD CIRCUIT
                            //C name sourceName maxCurrent
                            if (word[3].equals("ROOT")){
                                root = new Circuit("main",null,Integer.parseInt(word[4]));
                                System.out.println("Circuit '" + word[2] + "' added as root circuit");
                            }
                            else{
                                if (source.add(new Circuit(word[2], source, Integer.parseInt(word[4]))) == true){
                                    System.out.println("Circuit '" + word[2] + "' added successfully");
                                }
                                else{
                                    System.out.println("Circuit '" + word[2] + "' failed add operation!");
                                }

                            }
                            break;

                        case "R":
                            //ADD RECEPTACLE
                            //R name sourceName maxAttach
                            if (source.add(new Receptacle(word[2], source, Integer.parseInt(word[4]))) == true){
                                System.out.println("Receptacle '" + word[2] + "' added successfully");
                            }
                            else{
                                System.out.println("Receptacle '" + word[2] + "' failed add operation!");
                            }

                            break;

                        case "A":
                            //ADD APPLIANCE
                            //A name sourceName reqCurr
                            if (source.add(new Appliance(word[2], source, Integer.parseInt(word[4]))) == true){
                                System.out.println("Appliance '" + word[2] + "' added successfully");
                            }
                            else{
                                System.out.println("Appliance '" + word[2] + "' failed add operation!");
                            }

                            break;
                    }
                    break;

                case "T":
                    //TOGGLE APPLIANCE USE
                    //T name
                    if (findComponent(root,word[1]) instanceof Appliance){
                        Appliance subject = (Appliance)findComponent(root,word[1]);
                        String overload = subject.toggleUsage();
                        if (subject.getInUse()){
                            System.out.println(subject.name + " turned on.");

                            //HANDLES OVERLOAD
                            if (overload != null){
                                System.out.println(overload + " circuit overload!");
                                reset(findComponent(root, overload));
                            }
                        }
                        else{
                            System.out.println(subject.name + " turned off.");
                        }
                    }
                    else{
                        System.out.println("Toggle failure: '" + word[1] + "' is not an Appliance!");
                    }

                    break;
                case "D":
                    //DISPLAYS COMPONENT
                    Component toDisplay = findComponent(root,word[1]);
                    if (toDisplay != null){
                        toDisplay.display();
                    }
                    else{
                        System.out.println("Display failure: '" + word[1] + "' does not exist");
                    }

                    break;
                case "Q":
                    System.out.println("Quitting!");
                    System.exit(0);
                    break;

            }

        }
    }

    //Searches system for component given its name
    public static Component findComponent(Component source, String name){
        if (source != null){

            if (source.name.equals(name)){
                return source;
            }
            else{
                if (source instanceof Circuit){
                    Circuit obj = (Circuit) source;
                    for (Component c: obj.children){
                        Component test = findComponent(c, name);
                        if (test != null){
                            if (test.name.equals(name)){
                                return test;
                            }
                        }
                    }
                }
                else if (source instanceof Receptacle){
                    Receptacle obj = (Receptacle)source;
                    for (Component c: obj.children){
                        Component test = findComponent(c, name);
                        if (test != null){
                            if (test.name.equals(name)){
                                return test;
                            }
                        }
                    }
                }
            }

        }
        //RETURNS NULL IF CANNOT FIND COMPONENT
        return null;

        }

        //Resets all appliances that are connected to a given Component and updates current
        public static void reset(Component source){
            if (source != null){

                source.source.updateCurrent(-1 * source.currCurrent);
                source.reset();

                if (source instanceof Circuit){
                    Circuit obj = (Circuit) source;
                    for (Component c: obj.children){
                        reset(c);
                    }
                }
                else if (source instanceof Receptacle){
                    Receptacle obj = (Receptacle)source;
                    for (Component c: obj.children){
                        reset(c);
                    }
                }

            }

        }

}

