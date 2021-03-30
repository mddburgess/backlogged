import { Container } from "react-bootstrap";
import BacklogItemList from "../components/backlog/BacklogList";

const BacklogRoute = () => (
  <Container>
    <h2>Backlog</h2>
    <BacklogItemList />
  </Container>
);

export default BacklogRoute;
