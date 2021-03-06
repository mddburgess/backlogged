import { Button, OverlayTrigger, Tooltip } from "react-bootstrap";
import { JournalBookmark, JournalBookmarkFill } from "react-bootstrap-icons";
import { connect, ConnectedProps } from "react-redux";

import { StoreState } from "../../store";
import { actions } from "../../store/backlog";
import { TitleProps } from "../../types/Title";

const mapStateToProps = (state: StoreState) => ({
  backlog: state.backlog.data,
});
const mapDispatchToProps = {
  createBacklog: actions.create,
};
const connector = connect(mapStateToProps, mapDispatchToProps);

type Props = ConnectedProps<typeof connector> & TitleProps;

const BacklogToggle = ({ title, backlog, createBacklog }: Props) => {
  const addToBacklog = () => {
    void createBacklog(title);
  };

  return backlog.map((b) => b.title.key).includes(title.key) ? (
    <JournalBookmarkFill className="align-top mt-1" />
  ) : (
    <Button variant="link" className="align-top border-0 p-0" onClick={addToBacklog}>
      <OverlayTrigger placement="left" overlay={<Tooltip id="">Add to backlog</Tooltip>}>
        <JournalBookmark className="align-top mt-1" />
      </OverlayTrigger>
    </Button>
  );
};

export default connector(BacklogToggle);
