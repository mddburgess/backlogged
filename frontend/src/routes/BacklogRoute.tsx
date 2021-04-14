import { Container } from "react-bootstrap";

import BacklogList from "../components/backlog/BacklogList";

const BacklogRoute = () => (
  <Container>
    <h2>Backlog</h2>
    <BacklogList />
  </Container>
);

export default BacklogRoute;
