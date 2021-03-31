import { ListGroupItem } from "react-bootstrap";

import { BacklogProps } from "../../types/Backlog";

import RemoveFromBacklogButton from "./RemoveFromBacklogButton";

const BacklogRow = ({ backlog }: BacklogProps) => (
  <ListGroupItem className="px-2 py-1">
    <div className="d-flex justify-content-between">
      {backlog.title.name}
      <div>
        <RemoveFromBacklogButton backlog={backlog} />
      </div>
    </div>
  </ListGroupItem>
);

export default BacklogRow;
