package robots;


    public class BigRobot extends Observer{

        public BigRobot(Publisher publisher) {
            this.publisher=publisher;
            this.publisher.attach(this);
        }

        @Override
        public void update() {
            System.out.println("message received to Big Robot: "+publisher.getState());
        }
    }

