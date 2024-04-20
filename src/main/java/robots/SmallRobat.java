package robots;

public class SmallRobat extends Observer {

        public SmallRobat(Publisher publisher) {
            this.publisher=publisher;
            this.publisher.attach(this);
        }

        @Override
        public void update() {
            System.out.println("message received to small Robot: "+publisher.getState());
        }
    }

