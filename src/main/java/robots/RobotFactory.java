package robots;

public class RobotFactory {

    public Observer constructRobotObserver(RobotTypes type,Publisher publisher){
        if(type==null){
            return null;
        }
        if(type.equals(RobotTypes.ROBOT1)){
            return new Robot1(publisher);
        } else if(type.equals(RobotTypes.SMALLROBOT)){
            return new SmallRobat(publisher);
        }  else if(type.equals(RobotTypes.BIGROBOT)) {
            return new BigRobot(publisher);
        }else {
            return null;
        }
    }

    }

