import { Container } from "react-bootstrap";

import ActivityList from "../components/activities/ActivityList";

const ActivityLogRoute = () => (
  <Container>
    <h2>Activity Log</h2>
    <ActivityList />
  </Container>
);

export default ActivityLogRoute;
