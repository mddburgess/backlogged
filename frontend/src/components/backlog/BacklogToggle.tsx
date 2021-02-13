import React from "react";
import {Title} from "../../types/Title";
import {JournalBookmark, JournalBookmarkFill} from "react-bootstrap-icons";
import {StoreState} from "../../store";
import {connect, ConnectedProps} from "react-redux";
import {createBacklog} from "../../store/backlog";
import {Button, OverlayTrigger, Tooltip} from "react-bootstrap";

interface Props {
    title: Title;
}

const BacklogToggle = ({title, backlog, createBacklog}: Props & ReduxProps) => {

    const addToBacklog = () => {
        createBacklog(title);
    }

    return backlog.map(b => b.title.key).includes(title.key)
        ? <Button variant="link" className="p-0">
            <JournalBookmarkFill/>
        </Button>
        : <Button variant="link" className="p-0" onClick={addToBacklog}>
            <OverlayTrigger placement="auto" overlay={
                <Tooltip id="">Add to backlog</Tooltip>
            }>
                <JournalBookmark/>
            </OverlayTrigger>
        </Button>;

}

const mapStateToProps = (state: StoreState) => ({
    backlog: state.backlog.data
});
const mapDispatchToProps = {
    createBacklog
}
const connector = connect(mapStateToProps, mapDispatchToProps);
type ReduxProps = ConnectedProps<typeof connector>;
export default connector(BacklogToggle);
