import { Button, OverlayTrigger, Tooltip } from "react-bootstrap";
import { JournalBookmarkFill, JournalX } from "react-bootstrap-icons";
import React, { useState } from "react";
import { Backlog } from "../../types/Backlog";
import { actions } from "../../store/backlog";
import { connect, ConnectedProps } from "react-redux";

const RemoveFromBacklogButton = ({ backlog, deleteBacklog }: Props & ReduxProps) => {
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

interface Props {
  backlog: Backlog;
}

const mapDispatchToProps = {
  deleteBacklog: actions.delete,
};
const connector = connect(null, mapDispatchToProps);
type ReduxProps = ConnectedProps<typeof connector>;
export default connector(RemoveFromBacklogButton);
