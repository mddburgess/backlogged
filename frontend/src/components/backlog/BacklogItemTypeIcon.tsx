import {BacklogItem} from "types/BacklogItem";
import {Controller, Film, QuestionCircle} from "react-bootstrap-icons";

type Props = {
    backlogItem: BacklogItem;
}

export const BacklogItemTypeIcon = ({backlogItem}: Props) => {
    switch (backlogItem.type) {
        case "MOVIE":
            return <Film className="align-text-top me-2" style={{marginTop: "0.125rem"}}/>;
        case "VIDEO_GAME":
            return <Controller className="align-text-top me-2" style={{marginTop: "0.125rem"}}/>;
        default:
            return <QuestionCircle className="align-text-top me-2" style={{marginTop: "0.125rem"}}/>;
    }
}
