import { useEffect } from "react";
import { connect, ConnectedProps } from "react-redux";
import { ListGroup } from "react-bootstrap";
import { StoreState } from "../../store";
import { actions } from "../../store/backlog";
import BacklogRow from "./BacklogRow";

const BacklogList = ({ backlogItems, listBacklog }: ReduxProps) => {
  useEffect(() => {
    void listBacklog();
  }, [listBacklog]);

  return (
    <ListGroup>
      {backlogItems.map((item) => (
        <BacklogRow key={item.key} backlog={item} />
      ))}
    </ListGroup>
  );
};

const mapStateToProps = (state: StoreState) => ({
  backlogItems: state.backlog.data,
});
const mapDispatchToProps = {
  listBacklog: actions.list,
};
const connector = connect(mapStateToProps, mapDispatchToProps);
type ReduxProps = ConnectedProps<typeof connector>;
export default connector(BacklogList);
