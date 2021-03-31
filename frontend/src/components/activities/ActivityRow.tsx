import { DateTime } from "luxon";
import { ListGroupItem } from "react-bootstrap";
import { Icon, JournalMinus, JournalPlus, PlusSquare, QuestionCircle } from "react-bootstrap-icons";

import { ActivityProps } from "../../types/Activity";

import ActivityDetail from "./ActivityDetail";

const icons = new Map<string, Icon>([
  ["ADD_TO_BACKLOG", JournalPlus],
  ["ADD_TO_LIBRARY", PlusSquare],
  ["REMOVE_FROM_BACKLOG", JournalMinus],
]);

const ActivityRow = ({ activity }: ActivityProps) => {
  const IconElement = icons.get(activity.type) ?? QuestionCircle;
  return (
    <ListGroupItem className="px-2 py-1">
      <div className="d-flex justify-content-between align-items-center">
        <span>
          <IconElement className="mr-2" style={{ verticalAlign: "-.125em" }} />
          <ActivityDetail activity={activity} />
        </span>
        <span className="small text-muted">
          {DateTime.fromISO(activity.date).toRelativeCalendar()}
        </span>
      </div>
    </ListGroupItem>
  );
};

export default ActivityRow;
