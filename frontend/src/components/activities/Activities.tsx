import { Container, ListGroup } from "react-bootstrap";
import ActivityItem from "./ActivityItem";
import { StoreState } from "../../store";
import { connect, ConnectedProps } from "react-redux";
import React, { useEffect } from "react";
import { listActivities } from "../../store/activities";

const mapStateToProps = (state: StoreState) => ({
  activities: state.activities.data,
});
const mapDispatchToProps = {
  listActivities,
};
const connector = connect(mapStateToProps, mapDispatchToProps);
type ReduxProps = ConnectedProps<typeof connector>;

const Activities = ({ activities, listActivities }: ReduxProps) => {
  useEffect(() => {
    void listActivities();
  }, [listActivities]);

  return (
    <Container>
      <h2>Activity Log</h2>
      <ListGroup>
        {activities.map((activity) => (
          <ActivityItem activity={activity} />
        ))}
      </ListGroup>
    </Container>
  );
};

export default connector(Activities);
