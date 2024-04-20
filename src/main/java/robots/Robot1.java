package robots;

public class Robot1 extends Observer{

    public Robot1(Publisher publisher) {
        this.publisher=publisher;
        this.publisher.attach(this);
    }

    @Override
    public void update() {
        System.out.println("message received to robot 1: "+ publisher.getState() );
    }
}
