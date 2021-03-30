import { Container } from "react-bootstrap";
import Activities from "../components/activities/ActivityList";

const ActivityLogRoute = () => (
  <Container>
    <h2>Activity Log</h2>
    <Activities />
  </Container>
);

export default ActivityLogRoute;
