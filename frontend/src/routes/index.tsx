import { Route, Switch } from "react-router-dom";

import AddLibraryItem from "../components/AddLibraryTitle";
import EditLibraryItem from "../components/EditLibraryTitle";

import ActivityLogRoute from "./ActivityLogRoute";
import BacklogRoute from "./BacklogRoute";
import LibraryRoute from "./LibraryRoute";

const Routes = () => (
  <Switch>
    <Route exact path="/" component={ActivityLogRoute} />
    <Route path="/backlog" component={BacklogRoute} />
    <Route path="/library" component={LibraryRoute} />
    <Route path="/new" component={AddLibraryItem} />
    <Route path="/edit/:key" component={EditLibraryItem} />
  </Switch>
);

export default Routes;
