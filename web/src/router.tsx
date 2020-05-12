import React from 'react';
import { Route, Switch, Router } from 'react-router-dom';
import { HomePage, LoginPage, RegisterPage } from './pages';
import { createBrowserHistory } from 'history';

export const history = createBrowserHistory();

const AppRouter: React.FC = () => (
  <Router history={history}>
    <Switch>
      <Route path="/" exact component={HomePage} />
      <Route path="/login" exact component={LoginPage} />
      <Route path="/register" exact component={RegisterPage} />
    </Switch>
  </Router>
);

export default AppRouter;
