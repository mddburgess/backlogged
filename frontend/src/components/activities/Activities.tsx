import { Container, ListGroup } from "react-bootstrap";
import ActivityItem from "./ActivityItem";
import { Activity } from "../../types/Activity";

const activity: Activity = {
  type: "ADD_TO_BACKLOG",
  date: "2021-03-23T18:39:31.000-07:00",
  title: {
    key: "42",
    name: "The Legend of Zelda: Breath of the Wild",
    copies: [],
  },
};

const Activities = () => (
  <Container>
    <h2>Activity Log</h2>
    <ListGroup>
      <ActivityItem activity={activity} />
      <ActivityItem activity={activity} />
      <ActivityItem activity={activity} />
    </ListGroup>
  </Container>
);

export default Activities;
