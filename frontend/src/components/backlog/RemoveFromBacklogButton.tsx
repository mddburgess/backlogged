import { useState } from "react";
import { Button, OverlayTrigger, Tooltip } from "react-bootstrap";
import { JournalBookmarkFill, JournalX } from "react-bootstrap-icons";
import { connect, ConnectedProps } from "react-redux";

import { actions } from "../../store/backlog";
import { BacklogProps } from "../../types/Backlog";

const mapDispatchToProps = {
  deleteBacklog: actions.delete,
};
const connector = connect(null, mapDispatchToProps);

type Props = ConnectedProps<typeof connector> & BacklogProps;

const RemoveFromBacklogButton = ({ backlog, deleteBacklog }: Props) => {
  const [isHover, setHover] = useState(false);

  const removeFromBacklog = () => {
    void deleteBacklog(backlog);
  };

  return (
    <OverlayTrigger placement="left" overlay={<Tooltip id="">Remove from backlog</Tooltip>}>
      <Button
        variant="link"
        className="align-top border-0 p-0"
        onClick={removeFromBacklog}
        onMouseEnter={() => setHover(true)}
        onMouseLeave={() => setHover(false)}
      >
        {isHover ? (
          <JournalX className="text-danger align-top mt-1" />
        ) : (
          <JournalBookmarkFill className="align-top mt-1" />
        )}
      </Button>
    </OverlayTrigger>
  );
};

export default connector(RemoveFromBacklogButton);
