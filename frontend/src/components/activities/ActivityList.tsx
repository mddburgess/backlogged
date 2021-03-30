import { ListGroup } from "react-bootstrap";
import ActivityRow from "./ActivityRow";
import { StoreState } from "../../store";
import { connect, ConnectedProps } from "react-redux";
import { useEffect } from "react";
import { actions } from "../../store/activities";

const mapStateToProps = (state: StoreState) => ({
  activities: state.activities.data,
});
const mapDispatchToProps = {
  listActivities: actions.list,
};
const connector = connect(mapStateToProps, mapDispatchToProps);
type ReduxProps = ConnectedProps<typeof connector>;

const ActivityList = ({ activities, listActivities }: ReduxProps) => {
  useEffect(() => {
    void listActivities();
  }, [listActivities]);

  return (
    <ListGroup>
      {activities.map((activity) => (
        <ActivityRow activity={activity} />
      ))}
    </ListGroup>
  );
};

export default connector(ActivityList);
