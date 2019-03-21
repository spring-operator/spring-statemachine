_Have something you'd like to contribute to the framework? We welcome pull requests, but ask that you carefully read this document first to understand how best to submit them; what kind of changes are likely to be accepted; and what to expect from the Spring Statemachine team when evaluating your submission._

_Please refer back to this document as a checklist before issuing any pull request; this will save time for everyone!_

# Code of Conduct
This project adheres to the Contributor Covenant [code of conduct](CODE_OF_CONDUCT.adoc).
By participating, you  are expected to uphold this code. Please report unacceptable behavior to spring-code-of-conduct@pivotal.io.

# Similar but different

Each Spring module is slightly different than another in terms of team size, number of issues, etc. Therefore each project is managed slightly different. You will notice that this document is very similar to the [Spring Framework Contributor guidelines](https://github.com/SpringSource/spring-framework/wiki/Contributor-guidelines). However, there are some subtle differences between the two documents, so please be sure to read this document thoroughly.

# Importing into IDE

The following provides information on setting up a development environment that can run the sample in [Spring Tool Suite 3.6.0+](http://www.springsource.org/sts). Other IDE's should work using Gradle's IDE support, but have not been tested.

* IDE Setup
  * Install Spring Tool Suite 3.6.0+
  * You will need the following plugins installed (can be found on the Extensions Page)
   	* Gradle Eclipse
   	* Groovy Eclipse
* Importing the project into Spring Tool Suite
  * File->Import...->Gradle Project

# Understand the basics 
Not sure what a pull request is, or how to submit one? Take a look at GitHub's excellent [help documentation first](https://help.github.com/articles/using-pull-requests).

# Search JIRA first; create an issue if necessary
Is there already an issue that addresses your concern? Do a bit of searching in our [JIRA issue tracker](https://jira.springsource.org/browse/SEC) to see if you can find something similar. If not, please create a new issue before submitting a pull request unless the change is not a user facing issue.

# Discuss non-trivial contribution ideas with committers
If you are considering anything more than correcting a typo or fixing a minor bug , please discuss it on the GitHub issues. We are happy to provide guidance but please spend an hour or two researching the subject on your own including searching the forums for prior discussions.

# Sign the Contributor License Agreement
If you have not previously done so, please fill out and submit the [SpringSource CLA form](https://support.springsource.com/spring_committer_signup). You'll receive a token when this process is complete. Keep track of this, you may be asked for it later!

* For **Project** select _Spring Statemachine_
* For **Project Lead** enter _Janne Valkealahti_
* Note that emailing/postal mailing a signed copy is not necessary. Submission of the web form is all that is required.

When you've completed the web form, simply add the following in a comment on your pull request:

> I have signed and agree to the terms of the SpringSource Individual Contributor License Agreement.

You do not need to include your token/id. Please add the statement above to all future pull requests as well, simply so the Spring Statemachine team knows immediately that this process is complete.

# Create your branch from master
Create your topic branch to be submitted as a pull request from master. The Spring team will consider your pull request for backporting on a case-by-case basis; you don't need to worry about submitting anything for backporting.

# Use short branch names
Branches used when submitting pull requests should preferably be named according to JIRA issues, e.g. 'SEC-1234'. Otherwise, use succinct, lower-case, dash (-) delimited names, such as 'fix-warnings', 'fix-typo', etc. This is important, because branch names show up in the merge commits that result from accepting pull requests, and should be as expressive and concise as possible.

#Keep commits focused

Remember each JIRA should be focused on a single item of interest since the JIRA tickets are used to produce the changelog. Since each commit should be tied to a JIRA, ensure that your commits are focused. For example, do not include an update to a transitive library in your commit unless the JIRA is to update the library. Reviewing your commits is essential before sending a pull request.

# Mind the whitespace
Please carefully follow the whitespace and formatting conventions already present in the framework. 

1. Spaces, not tabs
1. Unix (LF), not dos (CRLF) line endings
1. Eliminate all trailing whitespace
1. Aim to wrap code at 120 characters, but favor readability over wrapping
1. Preserve existing formatting; i.e. do not reformat code for its own sake
1. Search the codebase using git grep and other tools to discover common naming conventions, etc.
1. Latin-1 (ISO-8859-1) encoding for Java sources; use native2ascii to convert if necessary

Whitespace management tips

1. You can use the [AnyEdit Eclipse plugin](http://marketplace.eclipse.org/content/anyedit-tools) to ensure spaces are used and to clean up trailing whitespaces.

# Add Apache license header to all new classes

<pre>
/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ...;
</pre>
# Update Apache license header to modified files as necessary
Always check the date range in the license header. For example, if you've modified a file in 2012 whose header still reads
<pre>
 * Copyright 2002-2011 the original author or authors.
</pre>
then be sure to update it to 2012 appropriately
<pre>
 * Copyright 2002-2012 the original author or authors.
</pre>
# Use @since tags for newly-added public API types and methods
e.g.
<pre>
/**
 * ...
 *
 * @author First Last
 * @since 3.2
 * @see ...
 */
</pre>

#Submit JUnit test cases for all behavior changes
Search the codebase to find related unit tests and add additional `@Test` methods within. 

1. Any new tests should end in the name Tests (note this is plural). For example, a valid name would be `FilterChainProxyTests`. An invalid name would be `FilterChainProxyTest`.
2. New test methods should not start with test. This is an old JUnit3 convention and is not necessary since the method is annotated with @Test.

# Squash commits
Use git rebase --interactive, git add --patch and other tools to "squash" multiple commits into atomic changes. In addition to the man pages for git, there are many resources online to help you understand how these tools work. Here is one: http://book.git-scm.com/4_interactive_rebasing.html.

# Use real name in git commits
Please configure git to use your real first and last name for any commits you intend to submit as pull requests. For example, this is not acceptable:

<pre>
Author: Nickname &lt;user@mail.com&gt;
</pre>
Rather, please include your first and last name, properly capitalized, as submitted against the SpringSource contributor license agreement:
<pre>
Author: First Last &lt;user@mail.com&gt;
</pre>
This helps ensure traceability against the CLA, and also goes a long way to ensuring useful output from tools like git shortlog and others.

You can configure this globally via the account admin area GitHub (useful for fork-and-edit cases); globally with

<pre>
git config --global user.name "First Last"
git config --global user.email user@mail.com
</pre>

or locally for the spring-statemachine repository only by omitting the '--global' flag:
<pre>
cd spring-statemachine
git config user.name "First Last"
git config user.email user@mail.com
</pre>

# Format commit messages

<pre>
SEC-1234: Short (50 chars or less) summary of changes

More detailed explanatory text, if necessary.  Wrap it to about 72
characters or so.  In some contexts, the first line is treated as the
subject of an email and the rest of the text as the body.  The blank
line separating the summary from the body is critical (unless you omit
the body entirely); tools like rebase can get confused if you run the
two together.

Further paragraphs come after blank lines.

 - Bullet points are okay, too

 - Typically a hyphen or asterisk is used for the bullet, preceded by a
   single space, with blank lines in between, but conventions vary here
</pre>


1. The commit subject should start with the associated jira issue followed by a : as shown in the example above
2. Keep the subject line to 50 characters or less if possible
3. Do not end the subject line with a period
4. In the body of the commit message, explain how things worked before this commit, what has changed, and how things work now

# Run all tests prior to submission

<pre>
cd spring-statemachine
./gradlew clean build
</pre>

# Submit your pull request
Subject line:

Follow the same conventions for pull request subject lines as mentioned above for commit message subject lines.

In the body:

1. Explain your use case. What led you to submit this change? Why were existing mechanisms in the framework insufficient? Make a case that this is a general-purpose problem and that yours is a general-purpose solution, etc
2. Add any additional information and ask questions; start a conversation, or continue one from JIRA
3. Mention the JIRA issue ID
4. Also mention that you have submitted the CLA as described above
Note that for pull requests containing a single commit, GitHub will default the subject line and body of the pull request to match the subject line and body of the commit message. This is fine, but please also include the items above in the body of the request.

# Mention your pull request on the associated JIRA issue
Add a comment to the associated JIRA issue(s) linking to your new pull request.

# Expect discussion and rework
The Spring team takes a very conservative approach to accepting contributions to the framework. This is to keep code quality and stability as high as possible, and to keep complexity at a minimum. Your changes, if accepted, may be heavily modified prior to merging. You will retain "Author:" attribution for your Git commits granted that the bulk of your changes remain intact. You may be asked to rework the submission for style (as explained above) and/or substance. Again, we strongly recommend discussing any serious submissions with the Spring Framework team prior to engaging in serious development work.

Note that you can always force push (git push -f) reworked / rebased commits against the branch used to submit your pull request. i.e. you do not need to issue a new pull request when asked to make changes.
