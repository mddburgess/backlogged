import { Container } from "react-bootstrap";
import { useParams } from "react-router-dom";

import TitleDetails from "../components/titles/TitleDetails";

const TitleRoute = () => {
  const params = useParams<{ key: string }>();

  return (
    <Container>
      <TitleDetails titleKey={params.key} />
    </Container>
  );
};

export default TitleRoute;
