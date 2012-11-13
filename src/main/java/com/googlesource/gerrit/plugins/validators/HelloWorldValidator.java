// Copyright (C) 2012 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.googlesource.gerrit.plugins.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gerrit.extensions.annotations.Listen;
import com.google.gerrit.server.events.CommitReceivedEvent;
import com.google.gerrit.server.git.validators.CommitValidationResult;
import com.google.gerrit.server.git.validators.CommitValidationListener;
import com.google.inject.Singleton;

@Listen
@Singleton
public class HelloWorldValidator implements CommitValidationListener {
  private static Logger log = LoggerFactory
      .getLogger(HelloWorldValidator.class);
  
  @Override
  public CommitValidationResult onCommitReceived(
      CommitReceivedEvent receiveEvent) {
    String commitFullMessage = receiveEvent.commit.getFullMessage();
    String commitMessage = commitFullMessage;
    
    if (commitMessage.indexOf("hello world") < 0) {
      log.warn("HelloWorldValidator> Commit "
          + receiveEvent.commit.getId().getName() + " REJECTED");
      return CommitValidationResult
          .newFailure("Only 'hello world' commit messages are allowed :-P");
    }
    log.info("HelloWorldValidator> Commit "
        + receiveEvent.commit.getId().getName() + " ACCEPTED");
    return CommitValidationResult.SUCCESS;
  }
}