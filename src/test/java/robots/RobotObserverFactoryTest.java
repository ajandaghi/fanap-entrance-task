package robots;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class RobotObserverFactoryTest {

    private static Observer robot1;
    private static Observer smallRobot;
    private static Observer bigRobot;

    private static RobotFactory robotFactory;

    private static final PrintStream standardOut = System.out;
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private static Publisher publisher;

    final private String os = System.getProperty("os.name");



    @BeforeEach
    public  void constructObjects(){
        robotFactory=new RobotFactory();
        publisher=new Publisher();
        robot1=robotFactory.constructRobotObserver(RobotTypes.ROBOT1,publisher);
        smallRobot=robotFactory.constructRobotObserver(RobotTypes.SMALLROBOT,publisher);
        bigRobot=robotFactory.constructRobotObserver(RobotTypes.BIGROBOT,publisher);
        System.setOut(new PrintStream(outputStreamCaptor));

    }

    @AfterEach
    public  void clearConsole(){
        outputStreamCaptor.reset();
    }

    @Test
    public void checkReceivingMessageByObservers(){
        publisher.setState("1");
        Assertions.assertEquals("message received to robot 1: 1\r\n" +
                "message received to small Robot: 1\r\n" +
                "message received to Big Robot: 1", outputStreamCaptor.toString()
                .trim());
    }


    @Test
    public void checkNumberOfReceivedMessage(){

        publisher.setState("1");
        publisher.setState("2");
        publisher.setState("3");

        //3 Observer and 3 Message from Publisher it becomes totally 9 Message + 3 before Message =12
        Assertions.assertEquals(9,outputStreamCaptor.toString().trim().split("\r\n").length);


    }

    @Test
    public void checkMethodFactoryWithWrongInput(){
        Observer tester=robotFactory.constructRobotObserver(null,publisher);
        Assertions.assertNull(tester);

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            robotFactory.constructRobotObserver(RobotTypes.valueOf("ROBOT2"),publisher);
        });

    }

    @Test
    public void checkMethodFactoryReturnsTrueObjects(){
        Observer tester1=robotFactory.constructRobotObserver(RobotTypes.ROBOT1,publisher);
        Observer tester2=robotFactory.constructRobotObserver(RobotTypes.SMALLROBOT,publisher);
        Observer tester3=robotFactory.constructRobotObserver(RobotTypes.BIGROBOT,publisher);

        Assertions.assertTrue(tester1 instanceof Robot1);
        Assertions.assertTrue(tester2 instanceof SmallRobat);
        Assertions.assertTrue(tester3 instanceof BigRobot);


    }

}