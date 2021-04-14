import { ListGroupItem } from "react-bootstrap";

import { TitleProps } from "../../types/Title";
import BacklogToggle from "../backlog/BacklogToggle";

import TitleDetailsLink from "./TitleDetailsLink";

const TitleRow = ({ title }: TitleProps) => (
  <ListGroupItem className="px-2 py-1">
    <div className="d-flex justify-content-between">
      <TitleDetailsLink title={title} />
      <BacklogToggle title={title} />
    </div>
    <ul className="mb-0">
      {title.copies.map((copy) => (
        <small>
          <li>
            {copy.platform} - {copy.service}
          </li>
        </small>
      ))}
    </ul>
  </ListGroupItem>
);

export default TitleRow;
