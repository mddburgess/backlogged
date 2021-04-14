import { Col, ListGroupItem, Row } from "react-bootstrap";

import { CopyProps } from "../../types/Title";

const CopyRow = ({ copy }: CopyProps) => (
  <ListGroupItem className="px-2 py-1">
    <Row>
      <Col>{copy.platform}</Col>
      <Col>{copy.service}</Col>
    </Row>
  </ListGroupItem>
);

export default CopyRow;
