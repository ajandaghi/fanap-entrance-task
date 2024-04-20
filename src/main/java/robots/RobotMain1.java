package robots;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
in scenario 1 : Three scheduled Thread Pool is defined. schedulers are called with one
Runnable Method and one iterator that causes race conditions. resource1 defines here as integer
in this scenario ThreadSafety is not considered and we receive irregular messages with repeated and not unique iterator

 **/

public class RobotMain1 {

    private static final ScheduledExecutorService scheduler1 = Executors.newScheduledThreadPool(1);
    private static final ScheduledExecutorService scheduler2= Executors.newScheduledThreadPool(1);
    private static final ScheduledExecutorService scheduler3= Executors.newScheduledThreadPool(1);

    public static Integer resource1 =1;
    public static RobotFactory robotFactory=new RobotFactory();

    public static Publisher publisher=new Publisher();

    public static void main(String[] args) {
        //deploying factory Method Design Pattern
        Observer robot1 = robotFactory.constructRobotObserver(RobotTypes.ROBOT1,publisher);
        Observer robot2 = robotFactory.constructRobotObserver(RobotTypes.SMALLROBOT,publisher);
        Observer robot3 =  robotFactory.constructRobotObserver(RobotTypes.BIGROBOT,publisher);

        //above really three robot observer has been subscribed to one available publisher

        scheduler1.scheduleAtFixedRate(new RunnableClass(publisher), 0, 3, TimeUnit.SECONDS);
        scheduler2.scheduleAtFixedRate(new RunnableClass(publisher), 0, 3, TimeUnit.SECONDS);
        scheduler3.scheduleAtFixedRate(new RunnableClass(publisher), 0, 3, TimeUnit.SECONDS);

        //defining 3 thread pool each with one core pool to define seperated schedule with fixed rate timing
        //to mock race condition

    }
}
   class RunnableClass implements Runnable{ //Race Condition on this method occurs
       public Publisher publisher;

       public RunnableClass(Publisher publisher) {
           this.publisher = publisher;


       }

       @Override
       public void run() {
           System.out.println("command "+ RobotMain1.resource1);
           publisher.setState(String.valueOf(RobotMain1.resource1++));

       }
   }
