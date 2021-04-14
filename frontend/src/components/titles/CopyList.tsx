import { ListGroup } from "react-bootstrap";

import { TitleProps } from "../../types/Title";

import CopyRow from "./CopyRow";

const CopyList = ({ title }: TitleProps) => (
  <ListGroup>
    {title.copies.map((copy) => (
      <CopyRow copy={copy} />
    ))}
  </ListGroup>
);

export default CopyList;
