import React from "react";
import {Title} from "../../types/Title";
import {JournalBookmark, JournalBookmarkFill} from "react-bootstrap-icons";
import {StoreState} from "../../store";
import {connect, ConnectedProps} from "react-redux";

interface Props {
    title: Title;
}

const BacklogToggle = ({title, backlog}: Props & ReduxProps) =>
    backlog.map(b => b.title.key).includes(title.key)
        ? <JournalBookmarkFill/>
        : <JournalBookmark/>;

const mapStateToProps = (state: StoreState) => ({
    backlog: state.backlog.data
});

const connector = connect(mapStateToProps);
type ReduxProps = ConnectedProps<typeof connector>;
export default connector(BacklogToggle);
